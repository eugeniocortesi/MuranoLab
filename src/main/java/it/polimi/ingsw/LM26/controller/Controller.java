package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.RoundsHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.UpdatesHandler;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.controllerHandler.EventHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Controller implements ControllerInt {

    private Model model;

    private RoundsHandler roundsHandler;

    private ViewGameInterface server;

    private UpdatesHandler updatesHandler;

    private SetupHandler setupHandler;

    private Boolean gameIsGoing;

    private ConcurrentLinkedQueue<ActionEvent> queueEvent;

    private PhaseInt gamePhase;

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    public Controller() {

        this.model = singletonModel();

        gameIsGoing = false;

        queueEvent = new ConcurrentLinkedQueue<ActionEvent>();
    }

    public void startServer() {

        updatesHandler = new UpdatesHandler(this);

        setupHandler = new SetupHandler(this, model);

        server = new ServerBase(updatesHandler);

        setupHandler.setServer(server);


        server.startAcceptor(updatesHandler, model);
    }

    public void setActionEvent(ActionEvent event) {

        if (event != null) {

            queueEvent.add(event);

            LOGGER.log(Level.SEVERE, "a new event has been setted: " + event);
        }

        else LOGGER.log(Level.SEVERE, "event nullo" + event);
    }

    public ActionEvent getActionEvent() {

        return queueEvent.poll();
    }

    @Override
    public boolean handler(ActionEvent event) {

        EventHandler handler = new EventHandler(event, model, this);

        return handler.getResult();
    }

    @Override
    public void playersReady() {

        gameIsGoing = true;

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            if (model.getPlayerList().get(i).getWindowPatternCard() == null && model.getPlayerList().get(i) != null) {

                gameIsGoing = false;
            }
        }

        if (gameIsGoing) {

            this.roundsHandler = new RoundsHandler(model, this);

            roundsHandler.start();
        }
    }

    public void declareScoresAndWinner(PlayerZone winner) {

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            if (winner.equals(model.getPlayerList().get(i)))

                server.showAnswerFromController(winner.getName(), "Sei il Vincitore");

            else
                server.showAnswerFromController(model.getPlayerList().get(i).getName(), winner.getName() + " è il vincitore");
        }
    }

    @Override
    public SetupHandler getSetupHandler() {

        return setupHandler;
    }

    @Override
    public ServerBase getServer() {

        return (ServerBase) server;
    }

    @Override
    public ViewGameInterface getViewGameInterface() {

        return server;
    }

    @Override
    public UpdatesHandler getUpdatesHandler() {

        return updatesHandler;
    }

    @Override
    public void setEndGame() {

        gamePhase.endGame();
    }

    @Override
    public void setGamePhase(PhaseInt phase) {

        this.gamePhase=phase;
    }
}


