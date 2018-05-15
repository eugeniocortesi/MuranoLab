package it.polimi.ingsw.LM26.networkServer.Data;

import com.google.gson.Gson;

public abstract class MessageClass {

    private String operation;

    //public abstract String serialize();

        /*Gson gson = new Gson();
        String protocolJson = gson.toJson(this);
        return protocolJson;*/


    //abstract public MessageClass deserialize(String protocolJson);

    public void execute(){
    }
}
