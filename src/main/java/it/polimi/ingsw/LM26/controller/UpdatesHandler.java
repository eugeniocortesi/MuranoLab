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

        //TODO
        //setActionEvent(actionEvent);

    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        System.out.println("Notify window arrived");
        controller.assignWindowCard(actionEventWindow.getName(), actionEventWindow.getWindowPatternCard());
    }

    @Override
    public void updateBeginGame(Boolean beginGame) {

        controller.setupWindowCard();
        controller.setupPrivateCard();

    }

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {

        //TODO esci dal while e passa il turno
        //timerEnd.getName(); nome di chi ha finito il tempo per la mossa
    }
}
