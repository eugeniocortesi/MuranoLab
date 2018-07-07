package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

/**
 * ConnectMessage class
 * @author Chiara Criscuolo
 * It is the class that manages the connection message
 */

public class ConnectMessage extends ClassMessage {

    String op;

    int field1;

    public ConnectMessage(String op, int name){

        this.op = op;

        this.field1 = name;
    }

    public int getField1() {

        return field1;
    }

    /**
     * Method that return from a string with json the ConnectMessage
     * @param protocolJson string to deserialize
     * @return ConnectMessage
     */

    static synchronized public ConnectMessage deserializeConnectMessage(String protocolJson){

        Gson gson = new Gson();

        return gson.fromJson(protocolJson, ConnectMessage.class);
    }

    /**
     * Shortcut to print it
     */

    public void dump() {

        System.out.println("Operation " +this.op+ " Field1 " +this.field1);
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitConnectMessage(this);
    }
}



