package it.polimi.ingsw.LM26.networkServer.Data;

import com.google.gson.Gson;

public class ClientLoginData extends MessageClass {

    String method;

    String username;

    public ClientLoginData(String op, String user){
        this.method = op;
        this.username = user;
    }

    public String getOperation() {
        return method;
    }

    public void setOperation(String op) {
        this.method = op;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String serializeLoginMessage(){

        Gson gson = new Gson();
        String msgJson = gson.toJson(this);
        return msgJson;
    }

    static public ClientLoginData deserialize(String protocolJson){
        Gson gson = new Gson();
        ClientLoginData message= gson.fromJson(protocolJson, ClientLoginData.class);
        return message;
    }


    public void execute() {

        System.out.println("Operation " +this.method+ " Username " +this.username);
    }
}
