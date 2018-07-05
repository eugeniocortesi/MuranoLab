package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.observers.serverController.*;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * UpdatesHandler class
 * @author Eugenio Cortesi
 * class that manages updates from Server
 */

public class UpdatesHandler implements Observer {

    Controller controller;

    private static final Logger LOGGER = Logger.getLogger(UpdatesHandler.class.getName());

    public UpdatesHandler(Controller controller) {

        LOGGER.setLevel(Level.ALL);

        this.controller = controller;
    }


    /**
     * method that receive and actionEventPlayer from Server
     * if player is connected creates a new PlayerZone, or set his state beck to the game
     * otherwise set standby Player
     * @param actionEventPlayer information about a player
     */

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {

        if (actionEventPlayer.isConnection())

            controller.getSetupHandler().setupPlayers(actionEventPlayer.getNamePlayer());

        else

            controller.getSetupHandler().setStandbyPlayer(actionEventPlayer.getNamePlayer());
    }


    /**
     * update-method receives the actionEvent object and sets it to the controller-queue
     * @param actionEvent game action from a client
     */

    @Override
    public void updateAction(ActionEvent actionEvent) {

        LOGGER.log(Level.INFO, "Arrived action event! ID: " + actionEvent.getId() + " Player: " + actionEvent.getPlayer());

        controller.setActionEvent(actionEvent);
    }


    /**
     * @param actionEventWindow choosen by a client, to be setted to him
     */

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.INFO, "Notify window arrived");

        controller.getSetupHandler().assignWindowCard(actionEventWindow.getName(), actionEventWindow.getWindowPatternCard());
    }

    /**
     * when this update is called means that the player list is done and closed.
     * the game can start, but before window pattern and private cards must be distributed
     * @param beginGame boolean information
     */

    @Override
    public void updateBeginGame(Boolean beginGame) {

        controller.getSetupHandler().setupWindowCard();

        controller.getSetupHandler().setupPrivateCard();
    }

    /**
     *
     * @param timerEnd event that updated the player list when a player ends his time to answer the window pattern card he wants
     */

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {

        if (timerEnd.getTimerEnd()) {

            LOGGER.log(Level.INFO, timerEnd.getName() + " client has finished his time");

            controller.getSetupHandler().deletePlayer(timerEnd);
        }
    }
}