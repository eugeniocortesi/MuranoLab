package it.polimi.ingsw.LM26.network.server;

//For each connection

public abstract class ClientHandlerInt extends Thread {

    public abstract void login (String username);

    public abstract void disconnect(String username);

}
