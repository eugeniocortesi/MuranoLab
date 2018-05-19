package it.polimi.ingsw.LM26.networkServer.ClientHandler;


import java.rmi.Remote;

public interface ClientInt extends Runnable, Remote {

    public void sendMessage(String message);

    public String receiveMessage();

    public void disconnect();

    public void login();

    public boolean isLogged();

    public void setLogged(boolean logged);

    public void run();

    public String getUsername();
}
