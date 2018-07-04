package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;

/**
 * ActionEventPlayer class
 * @author Chiara Criscuolo
 * Class that contains the state of the connection of the player
 */

public class ActionEventPlayer extends ClassMessage implements Serializable {

    private String namePlayer;

    private boolean connection;

    /**
     * Constructor
     * @param m username of the player
     * @param connection boolean of the state of the connection
     */

    public ActionEventPlayer(String m, boolean connection){
        this.namePlayer=m;
        this.connection=connection;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEventPlayer(this);
    }
}
