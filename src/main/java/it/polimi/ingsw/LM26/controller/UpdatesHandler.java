package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UpdatesHandler class
 * @author Eugenio Cortesi
 * Class that manages updates from Server
 */
public class UpdatesHandler implements Observer {

    Controller controller;
    private static final Logger LOGGER = Logger.getLogger(UpdatesHandler.class.getName());

    /**
     * Constructor
     * @param controller reference to Controller
     */

    public UpdatesHandler(Controller controller) {this.controller= controller;
    }

    /**
     * Method that receive and actionEventPlayer from Server
     * if player is connected => creates a new PlayerZone
     * Otherwise set Stanby Player
     * @param actionEventPlayer information about a player
     */
    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {

        if(actionEventPlayer.isConnection())

            controller.getSetupHandler().setupPlayers(actionEventPlayer.getNamePlayer());
        else

            controller.setStandbyPlayer(actionEventPlayer.getNamePlayer());

    }

    @Override
    public void updateAction(ActionEvent actionEvent) {

        LOGGER.log(Level.INFO,"Arrived action event "+ actionEvent);
        System.out.println("Arrived action event! ID: "+ actionEvent.getId()+ " Player: "+ actionEvent.getPlayer());
        controller.setActionEvent(actionEvent);

    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.INFO,"Notify window arrived");
        controller.getSetupHandler().assignWindowCard(actionEventWindow.getName(), actionEventWindow.getWindowPatternCard());
    }

    @Override
    public void updateBeginGame(Boolean beginGame) {

        controller.setupWindowCard();
        controller.setupPrivateCard();

    }

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {

        //There are 2 players and timer end -> begin game
        if(timerEnd.getName().equals("ready") && timerEnd.getTimerEnd()){

            LOGGER.log(Level.INFO,"Game start!");

        }
        //A player has end his time to do the action
        else if(timerEnd.getTimerEnd()){

            LOGGER.log(Level.INFO,timerEnd.getName()+ " client has finished his time");


           //controller.getSetupHandler().setUpJumpTurn(timerEnd);
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
