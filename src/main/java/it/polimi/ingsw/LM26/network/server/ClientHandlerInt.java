package it.polimi.ingsw.LM26.network.server;

public interface ClientHandlerInt {

    public void login (String username);

    void disconnect(String username);
}
