package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class DataMessage {

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

    public String parserFirstElement(String s){
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

    public String serializeDataMessage(){

        Gson gson = new Gson();
        String msgJson = gson.toJson(this);
        return msgJson;
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
    public String toString(){
        return this.serializeDataMessage();
    }
}

