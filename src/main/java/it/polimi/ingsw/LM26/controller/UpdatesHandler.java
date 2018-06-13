package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.*;

public class UpdatesHandler implements Observer {

    Controller controller;

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

        //TODO
        //setActionEvent(actionEvent);

    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        System.out.println("Notify window arrived");
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

            System.out.println("Game start!");

        }
        //A player has end his time to do the action
        else if(timerEnd.getTimerEnd()){
            System.out.println(timerEnd.getName()+ " client has finished his time");
            //The current player has finished  his time
            //TODO esci dal while e passa il turno
            //timerEnd.getName(); nome di chi ha finito il tempo per la mossa
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
