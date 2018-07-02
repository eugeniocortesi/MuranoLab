package it.polimi.ingsw.LM26.systemNetwork.clientNet;


/**
 * Abstract class ClientInt
 * @author Chiara Criscuolo
 * Used to begin the game with GUI or CLI
 */
public abstract class ClientInt {

    ViewInterface view;

    boolean connection;

    String username;

    public void setConnection(boolean connection){
        this.connection = connection;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
