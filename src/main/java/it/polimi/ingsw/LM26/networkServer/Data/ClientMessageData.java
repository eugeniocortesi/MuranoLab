package it.polimi.ingsw.LM26.networkServer.Data;


import com.google.gson.Gson;

public class ClientMessageData extends MessageClass {

    private String operation;

    private String field1;

    private String field2;



    public ClientMessageData(String a, String b, String c){

        this.operation= a;
        this.field1 = b;
        this.field2 = c;
    }

    public ClientMessageData(String a, String b){

        this.operation = a;
        this.field1= b;
        this.field2 = null;
    }

    public ClientMessageData(String op){
        this.operation = op;
        this.field1 = null;
        this.field2 = null;
    }

    public String getOperazione() {
        return operation;
    }

    public void setOperazione(String op) {
        this.operation = op;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String serialize(){

        Gson gson = new Gson();
        String protocolJson = gson.toJson(this);
        return protocolJson;
    }

    static public ClientMessageData deserialize(String protocolJson){
        Gson gson = new Gson();
        ClientMessageData message= gson.fromJson(protocolJson, ClientMessageData.class);
        return message;
    }

    public void execute() {

        System.out.println("Operation " +operation+ " Field1 " +field1+ " Field2 " +field2);
    }
}
