package it.polimi.ingsw.LM26.network.server;


import java.util.Observable;
import java.util.Observer;

public abstract class ClientHandlerInt extends Thread {

    public abstract void login (String username);

    public abstract void disconnect(String username);

}
