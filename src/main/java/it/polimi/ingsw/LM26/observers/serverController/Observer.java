package it.polimi.ingsw.LM26.observers.serverController;

/**
 * Observer interface
 * @author Chiara Criscuolo
 * @param <T> generic type
 * It uses overloading to call the different update
 */

public interface Observer<T> {

    void updatePlayers(ActionEventPlayer actionEventPlayer);

    void updateAction(ActionEvent actionEvent);

    void updateWindowPattern(ActionEventWindow actionEventWindow);

    void updateBeginGame(Boolean beginGame);

    void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd);

}
