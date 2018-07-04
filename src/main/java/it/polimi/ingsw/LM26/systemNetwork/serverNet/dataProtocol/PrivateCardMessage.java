package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;

/**
 * PrivateCardMessage class
 * @author Chiara Criscuolo
 * It contains the private card
 */

public class PrivateCardMessage extends ClassMessage{

    private String idPrivateCard;

    private ObjectivePrivateCard privateCard;

    public PrivateCardMessage(String idPrivateCard, ObjectivePrivateCard privateCard) {

        this.idPrivateCard = idPrivateCard;

        this.privateCard = privateCard;
    }

    public ObjectivePrivateCard getPrivateCard() {
        return privateCard;
    }

    /**
     * Method that return from a string with json the PrivateCardMessage
     * @param protocolJson string to deserialize
     * @return PrivateCardMessage
     */

    static public PrivateCardMessage deserializeEventMessage(String protocolJson){

        Gson gson = new Gson();

        return gson.fromJson(protocolJson, PrivateCardMessage.class);
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
