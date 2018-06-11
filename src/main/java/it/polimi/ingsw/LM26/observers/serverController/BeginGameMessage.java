package it.polimi.ingsw.LM26.observers.serverController;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

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

    public Boolean getConnection() {
        return connection;
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
