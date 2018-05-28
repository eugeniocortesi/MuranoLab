package it.polimi.ingsw.LM26.systemNetwork.serverNet;

public class ClientManagerSocket implements ClientManager {

    private ListenerClientManager listenerClientManager;

    public ClientManagerSocket(){
        listenerClientManager = new ListenerClientManager();
    }

    @Override
    public void connect() {

    }

    @Override
    public int getAvailableId() {
        return 0;
    }

    @Override
    public void requestedLogin() {

    }

    @Override
    public void login(String name) {

    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void disconnect() {

    }
}
