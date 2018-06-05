package it.polimi.ingsw.LM26.networkServer.ClientHandler;


public interface ClientInt extends Runnable {

    public void disconnect();

    public void login();

    public boolean isLogged();

    public void setLogged(boolean logged);

    public void run();

    public String getUsername();
}
