package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

/**
 * DataMessage class
 * @author Chiara Criscuolo
 * Class that manages generic messages
 */

public class DataMessage extends ClassMessage {

    String operation;

    String field1;

    public DataMessage(String op, String name){

        this.operation = op;

        this.field1 = name;
    }

    public String getField1() {

        return field1;
    }

    /**
     * Method that return from a string with json the DataMessage
     * @param protocolJson string to deserialize
     * @return DataMessage
     */

    static synchronized public DataMessage deserializeDataMessage(String protocolJson){
        Gson gson = new Gson();

        return gson.fromJson(protocolJson, DataMessage.class);
    }

    /*
    Shortcut to print it
     */

    public void dump() {

        System.out.println("Operation " +this.operation+ " Field1 " +this.field1);
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitDataMessage(this);
    }
}

