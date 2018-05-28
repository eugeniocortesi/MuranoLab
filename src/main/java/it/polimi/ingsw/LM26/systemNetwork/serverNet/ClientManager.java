package it.polimi.ingsw.LM26.systemNetwork.serverNet;

public interface ClientManager {

    void connect();

    int getAvailableId();

    void requestedLogin();

    void login(String name);

    void logged(Boolean l, String name);

    void disconnect();
}
