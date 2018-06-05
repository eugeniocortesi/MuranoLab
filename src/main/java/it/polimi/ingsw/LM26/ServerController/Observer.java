package it.polimi.ingsw.LM26.ServerController;

public interface Observer<T> {

    public void update (T message);
}
