package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

public class ConnectMessage extends ClassMessage {
    String op;
    int field1;

    public ConnectMessage(String op, int name){
        this.op = op;
        this.field1 = name;
    }

    public String getOperation() {
        return op;
    }

    public int getField1() {
        return field1;
    }

    static public ConnectMessage deserializeConnectMessage(String protocolJson){
        Gson gson = new Gson();
        ConnectMessage message= gson.fromJson(protocolJson, ConnectMessage.class);
        return message;
    }
    public void dump() {

        System.out.println("Operation " +this.op+ " Field1 " +this.field1);
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitConnectMessage(this);
    }
}



