package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

/**
 * PlayerConnectionMessage class
 * @author Chiara Criscuolo
 * It contains the status of the connection
 */

public class PlayerConnectionMessage extends ClassMessage {

    private String op;

    private Boolean connection;

    public PlayerConnectionMessage(String op, Boolean connection) {
        this.op = op;
        this.connection = connection;
    }

    public Boolean getConnection() {
        return connection;
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitPlayerConnectionMessage(connection);
    }
}
