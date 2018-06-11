package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

public class BeginTurnMessage extends ClassMessage {

    private String idBeginTurn;

    private String username;

    private PlayerZone playerZone;

    public BeginTurnMessage(String idBeginTurn, String username, PlayerZone playerZone) {
        this.idBeginTurn = idBeginTurn;
        this.username = username;
        this.playerZone = playerZone;
    }

    public String getUsername(){
        return this.username;
    }

    public PlayerZone getPlayerZone() {
        return playerZone;
    }

    static public BeginTurnMessage deserializeDataMessage(String protocolJson){
        Gson gson = new Gson();
        BeginTurnMessage message= gson.fromJson(protocolJson, BeginTurnMessage.class);
        return message;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitBeginTurnMessage(this);
    }
}

