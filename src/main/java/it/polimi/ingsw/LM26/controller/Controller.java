package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.EventHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.RoundsHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.UpdatesHandler;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

/**
 * Controller class
 * @author Eugenio Cortesi
 */

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

        queueEvent = new ConcurrentLinkedQueue<>();
    }


    /**
     * method that starts server and creates all the extensions of controller
     */

    public void startServer() {

        updatesHandler = new UpdatesHandler(this);

        setupHandler = new SetupHandler(this, model);

        server = new ServerBase(updatesHandler);

        setupHandler.setServer(server);

        server.startAcceptor(updatesHandler, model);
    }


    /**
     * when the update gets an actionEvent this method is called, and it stores the event in a concurrent queue
     * @param event event got from server
     */

    public void setActionEvent(ActionEvent event) {

        if (event != null)

            queueEvent.add(event);

            LOGGER.log(Level.SEVERE, "a new event has been set: " + event);
    }

    @Override
    public ActionEvent getActionEvent() {

        return queueEvent.poll();
    }


    /**
     * when an event arrives in the roundsHandler and has to be handled this method is called
     * @param event actionEvent to be examined
     * @return result from eventHandler
     */

    @Override
    public boolean handler(ActionEvent event) {

        EventHandler handler = new EventHandler(event, model, this);

        return handler.getResult();
    }

    /**
     * this method is called every time that a client responds to the window pattern request.
     * if some players haven't already respond the game don't start and the method end, this
     * until every players has his window pattern set, condition (gameIsGoing) permits the
     * creation of roundsHandler and the start of the game
     */

    @Override
    public void playersReady() {

        gameIsGoing = true;

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            if (model.getPlayerList().get(i).getWindowPatternCard() == null) {

                gameIsGoing = false;
            }
        }

        if (gameIsGoing) {

            this.roundsHandler = new RoundsHandler(model, this);

            roundsHandler.start();
        }
    }

    /**
     * when game ended and finalPhase methods have been executed, this methos communicates to the clients their points and the winner
     * @param winner client that won the game
     */

    public void declareScoresAndWinner(PlayerZone winner) {

        for (int i = 0; i < model.getPlayerList().size(); i++)

            server.showEndGame(model.getPlayer(i).getName(), model.getPlayer(i).getScoreMarker().getPoints(), winner.getName(), winner.getScoreMarker().getPoints());
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
    public void setEndGame(PlayerZone last) {

        gamePhase.endGame(last);
    }

    @Override
    public void setGamePhase(PhaseInt phase) {

        this.gamePhase=phase;
    }

    @Override
    public RoundsHandler getRoundsHandler() {
        return roundsHandler;
    }
}


