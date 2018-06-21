package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdatesHandler implements Observer {

    Controller controller;
    private static final Logger LOGGER = Logger.getLogger(UpdatesHandler.class.getName());

    public UpdatesHandler(Controller controller) {this.controller= controller;
    }

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
            //The current player has finished  his time
            //TODO esci dal while e passa il turno
            //timerEnd.getName(); nome di chi ha finito il tempo per la mossa
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
