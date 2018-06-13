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

    private Model model;

    public ModelMessage(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    static public Model deserializeModelMessage(String protocolJson){

        Type modelType = new TypeToken<Model>() {
        }.getType();

        RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1
                .of(Effect.class, "type")
                .registerSubtype(DifferentColorShadeOnRowColomn.class)
                .registerSubtype(DifferentColorShade.class)
                .registerSubtype(Shades.class)
                .registerSubtype(ColoredDiagonals.class);

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory1).create();


        Model model= gson.fromJson(protocolJson, modelType);
        System.out.println("playerlist "+ model.getPlayerList());
        for(int i = 0; i<model.getPlayerList().size(); i++){
            System.out.println(model.getPlayerList().get(i)+ "player");
        }

        return model;
    }

    @Override
    public String serializeClassMessage(){

        Gson gson = new GsonBuilder().create();
        String msgJson = gson.toJson(this.model);
        return msgJson;
    }


    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
