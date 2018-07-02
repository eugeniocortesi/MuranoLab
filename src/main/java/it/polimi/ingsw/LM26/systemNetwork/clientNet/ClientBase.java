package it.polimi.ingsw.LM26.systemNetwork.clientNet;

/**
 * Concrete Implementation of ClientInt
 * @author Chiara Criscuolo
 */
public class ClientBase extends ClientInt {

    public void setView(ViewInterface view) {
        this.view = view;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

}
