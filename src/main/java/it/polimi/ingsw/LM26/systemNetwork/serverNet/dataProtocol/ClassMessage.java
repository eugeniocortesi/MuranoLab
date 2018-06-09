package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ClassMessage {

    private static final Logger LOGGER = Logger.getLogger(ClassMessage.class.getName());


    public String parserFirstElement(String s){

        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);

        JsonReader jsonReader = new JsonReader(new StringReader(s));
        try{
            while(jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();
                //System.out.println(nextToken);
                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                    jsonReader.beginObject();
                } else if (JsonToken.NAME.equals(nextToken)) {
                    String name = jsonReader.nextName();
                    LOGGER.log(Level.INFO,name);
                } else if (JsonToken.STRING.equals(nextToken)) {
                    String value = jsonReader.nextString();
                    LOGGER.log(Level.INFO,value);
                    return value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String serializeClassMessage(){

        Gson gson = new Gson();
        String msgJson = gson.toJson(this);
        return msgJson;
    }

    @Override
    public String toString(){
        return this.serializeClassMessage();
    }

    public abstract void accept(VisitorInt visitorInt);
}
