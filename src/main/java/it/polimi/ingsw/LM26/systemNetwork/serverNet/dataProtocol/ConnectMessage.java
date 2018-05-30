package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

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

    /*public String parserFirstElement(String s){
        JsonReader jsonReader = new JsonReader(new StringReader(s));
        try{
            while(jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();
                System.out.println(nextToken);
                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                    jsonReader.beginObject();
                } else if (JsonToken.NAME.equals(nextToken)) {
                    String name = jsonReader.nextName();
                    System.out.println(name);
                } else if (JsonToken.STRING.equals(nextToken)) {
                    String value = jsonReader.nextString();
                    System.out.println(value);
                    return value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String serializeConnectMessage(){

        Gson gson = new Gson();
        String msgJson = gson.toJson(this);
        return msgJson;
    }*/

    static public ConnectMessage deserializeConnectMessage(String protocolJson){
        Gson gson = new Gson();
        ConnectMessage message= gson.fromJson(protocolJson, ConnectMessage.class);
        return message;
    }
    public void dump() {

        System.out.println("Operation " +this.op+ " Field1 " +this.field1);
    }
}



