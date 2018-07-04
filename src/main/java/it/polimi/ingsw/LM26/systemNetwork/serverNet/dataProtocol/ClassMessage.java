package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassMessage abstract class
 * @author Chiara Criscuolo
 * It is the base class for all socket messages
 */

public abstract class ClassMessage {

    private static final Logger LOGGER = Logger.getLogger(ClassMessage.class.getName());

    /**
     * Method that parse each message and extracts only the first field (with json)
     * @param s message to parse
     * @return String = first element of s
     */

    public String parserFirstElement(String s){

        LOGGER.setLevel(Level.OFF);

        JsonReader jsonReader = new JsonReader(new StringReader(s));
        //jsonReader.setLenient(true);
        try{
            while(jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();

                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                    jsonReader.beginObject();
                } else if (JsonToken.NAME.equals(nextToken)) {
                    String name = jsonReader.nextName();
                } else if (JsonToken.STRING.equals(nextToken)) {
                    return jsonReader.nextString();
                } else{
                    LOGGER.log(Level.INFO,"null");
                }
            }
        } catch (IOException e) {
            System.err.println("Enable to parse message");
            return null;
        }
        return null;
    }

    /**
     * Method that serializes each message using Gson
     * @return serialized message (String)
     */
    public String serializeClassMessage(){

        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Method that returns from the message the serialized one
     * @return message serialized
     */
    @Override
    public String toString(){
        return this.serializeClassMessage();
    }

    /**
     * Method that calls for each message the visitor pattern that recognize it
     * @param visitorInt instance of visitor
     */
    public abstract void accept(VisitorInt visitorInt);

}
