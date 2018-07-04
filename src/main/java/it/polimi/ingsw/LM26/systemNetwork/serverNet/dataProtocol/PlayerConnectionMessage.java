package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
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
     * Method that return from a string with json the PlayerConnectionMessage
     * @param protocolJson string to deserialize
     * @return PlayerConnectionMessage
     */

    static public PlayerConnectionMessage deserializeEventMessage(String protocolJson){

        Gson gson = new Gson();

        return gson.fromJson(protocolJson, PlayerConnectionMessage.class);
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
