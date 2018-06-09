package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

public class BeginGameMessage extends ClassMessage {
    private String op;
    private Boolean connection;

    public BeginGameMessage(String op, Boolean connection) {
        this.op = op;
        this.connection = connection;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    static public BeginGameMessage deserializeEventMessage(String protocolJson){
        Gson gson = new Gson();
        BeginGameMessage message= gson.fromJson(protocolJson, BeginGameMessage.class);
        return message;
    }


    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitBeginGame(connection);
    }
}
