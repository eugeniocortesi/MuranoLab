package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

public class PlayerConnectionMessage extends ClassMessage {

    private String op;
    private Boolean connection;

    public PlayerConnectionMessage(String op, Boolean connection) {
        this.op = op;
        this.connection = connection;
    }

    public String getOp() {
        return op;
    }

    public Boolean getConnection() {
        return connection;
    }

    static public PlayerConnectionMessage deserializeEventMessage(String protocolJson){
        Gson gson = new Gson();
        PlayerConnectionMessage message= gson.fromJson(protocolJson, PlayerConnectionMessage.class);
        return message;
    }


    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitPlayerConnectionMessage(connection);
    }
}
