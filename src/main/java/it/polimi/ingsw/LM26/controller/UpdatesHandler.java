package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.ServerController.*;

public class UpdatesHandler implements Observer {

    Controller controller;

    public UpdatesHandler(Controller controller) {this.controller= controller;
    }

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {

        if(actionEventPlayer.isConnection())

            controller.setupPlayers(actionEventPlayer.getNamePlayer());
        else

            controller.setStandbyPlayer(actionEventPlayer.getNamePlayer());

    }

    @Override
    public void updateAction(ActionEvent actionEvent) {
        //setActionEvent(actionEvent);

    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        //assegna ogni carta al player
        System.out.println("Notify window arrived");
        controller.assignWindowCard(actionEventWindow.getName(), actionEventWindow.getWindowPatternCard());
    }

    @Override
    public void updateBeginGame(Boolean beginGame) {

        controller.setupWindowCard();
        controller.setupPrivateCard();

        //TODO create new match
    }

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {

        //TODO esci dal while e passa il turno
        //timerEnd.getName(); nome di chi ha finito il tempo per la mossa
    }
}
