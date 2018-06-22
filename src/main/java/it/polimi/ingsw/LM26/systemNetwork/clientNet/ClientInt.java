package it.polimi.ingsw.LM26.systemNetwork.clientNet;

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
