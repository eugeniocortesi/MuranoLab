package it.polimi.ingsw.LM26.ServerController;

public interface Observer<T> {

    void update (T message);

    void updatePlayers(ActionEventPlayer actionEventPlayer);

    void updateAction(ActionEvent actionEvent);
}
