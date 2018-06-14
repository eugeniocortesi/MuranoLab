package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Serialization.*;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;
import it.polimi.ingsw.LM26.model.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModelMessage extends ClassMessage{

    private String idModel;

    private String model;

    public ModelMessage(String m, String model) {
        this.idModel = m;
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    static public ModelMessage deserializeModelMessage(String protocolJson){


        Gson gson = new Gson();

        ModelMessage model= gson.fromJson(protocolJson, ModelMessage.class );


        return model;
    }

    @Override
    public String serializeClassMessage(){

        Gson gson = new GsonBuilder().create();
        String msgJson = gson.toJson(this);
        return msgJson;
    }


    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
