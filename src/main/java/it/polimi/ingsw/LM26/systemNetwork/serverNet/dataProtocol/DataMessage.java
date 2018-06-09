package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

public class DataMessage extends ClassMessage {

    String operation;
    String field1;

    public DataMessage(String op, String name){
        this.operation = op;
        this.field1 = name;
    }

    public String getOperation() {
        return operation;
    }

    public String getField1() {
        return field1;
    }

    static public DataMessage deserializeDataMessage(String protocolJson){
        Gson gson = new Gson();
        DataMessage message= gson.fromJson(protocolJson, DataMessage.class);
        return message;
    }

    public void dump() {

        System.out.println("Operation " +this.operation+ " Field1 " +this.field1);
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitDataMessage(this);
    }
}

