package it.polimi.ingsw.LM26.ServerController;

public interface Observer<T> {


    void updatePlayers(ActionEventPlayer actionEventPlayer);

    void updateAction(ActionEvent actionEvent);

    void updateWindowPattern(ActionEventWindow actionEventWindow);
}
