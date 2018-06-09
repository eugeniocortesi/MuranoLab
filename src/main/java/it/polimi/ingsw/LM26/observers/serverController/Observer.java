package it.polimi.ingsw.LM26.observers.serverController;

public interface Observer<T> {


    void updatePlayers(ActionEventPlayer actionEventPlayer);

    void updateAction(ActionEvent actionEvent);

    void updateWindowPattern(ActionEventWindow actionEventWindow);

    void updateBeginGame(Boolean beginGame);

    void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd);

}
