package it.polimi.ingsw.LM26.systemNetwork.clientNet;

public interface ClientView {

    void connect();

    void requestedLogin();

    void login ( String name );

    void logged ( Boolean l, String name );

    void tooManyUsers();

    void disconnect();
}
