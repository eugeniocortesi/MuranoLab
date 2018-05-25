package it.polimi.ingsw.LM26.network.server;


import java.util.Observable;
import java.util.Observer;

public abstract class ClientHandlerInt extends Observable {

    public abstract void login (String username);

    public abstract void disconnect(String username);

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged();
    }

}
