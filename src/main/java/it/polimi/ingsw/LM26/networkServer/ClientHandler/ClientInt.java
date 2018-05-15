package it.polimi.ingsw.LM26.networkServer.ClientHandler;


public interface ClientInt extends Runnable {

    public void sendMessage(String message);

    public String receiveMessage();

    public void disconnect();

    public void login();

    public boolean isLogged();

    public void setLogged(boolean logged);

    public void run();
}
