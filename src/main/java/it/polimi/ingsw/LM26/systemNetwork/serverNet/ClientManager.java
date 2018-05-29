package it.polimi.ingsw.LM26.systemNetwork.serverNet;

public abstract class ClientManager extends Thread {

    public abstract void connect();

    public abstract int getAvailableId();

    public abstract void requestedLogin();

    public abstract void login(String name);

    public abstract void logged(Boolean l, String name);

    public abstract void disconnect();

    public abstract void run();
}
