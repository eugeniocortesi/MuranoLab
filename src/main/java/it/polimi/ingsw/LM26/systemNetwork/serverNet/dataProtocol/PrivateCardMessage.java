package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;

public class PrivateCardMessage extends ClassMessage{

    private String idPrivateCard;

    private ObjectivePrivateCard privateCard;

    public PrivateCardMessage(String idPrivateCard, ObjectivePrivateCard privateCard) {
        this.idPrivateCard = idPrivateCard;
        this.privateCard = privateCard;
    }

    public String getIdPrivateCard() {
        return idPrivateCard;
    }

    public ObjectivePrivateCard getPrivateCard() {
        return privateCard;
    }

    static public PrivateCardMessage deserializeEventMessage(String protocolJson){
        Gson gson = new Gson();
        PrivateCardMessage message= gson.fromJson(protocolJson, PrivateCardMessage.class);
        return message;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
