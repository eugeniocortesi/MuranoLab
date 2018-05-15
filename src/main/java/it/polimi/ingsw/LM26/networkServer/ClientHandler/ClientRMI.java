package it.polimi.ingsw.LM26.networkServer.ClientHandler;

public class ClientRMI implements ClientInt {

    //scrittura su RMI bloccante per il client che chiede al server

    public void sendMessage(String message) {

    }

    public String receiveMessage() {
        return null;
    }

    public void disconnect() {
        ;
    }

    public void login() {

    }

    public boolean isLogged() {
        return false;
    }

    public void setLogged(boolean logged) {

    }

    public void run() {

    }

    public void start() {
        run();
    }
}
