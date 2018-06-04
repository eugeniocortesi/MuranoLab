package it.polimi.ingsw.LM26.systemNetwork.ServerController;

public interface Observer<T> {

    public void update (T message);
}
