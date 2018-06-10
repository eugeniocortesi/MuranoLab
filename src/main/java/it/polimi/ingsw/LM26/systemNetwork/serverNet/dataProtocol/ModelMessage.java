package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Serialization.*;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;
import it.polimi.ingsw.LM26.model.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModelMessage extends ClassMessage{

    private String idModel;
    private Model model;

    public ModelMessage(String idModel, Model model) {
        this.idModel = idModel;
        this.model = model;
    }

    public String getIdModel() {
        return idModel;
    }

    public Model getModel() {
        return model;
    }

    static public Model deserializeModelMessage(String protocolJson){


        Gson gson = new Gson();

        Type modelType = new TypeToken<Model>() {
        }.getType();

        RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1
                .of(Effect.class, "type")
                .registerSubtype(DifferentColorShadeOnRowColomn.class)
                .registerSubtype(DifferentColorShade.class)
                .registerSubtype(Shades.class)
                .registerSubtype(ColoredDiagonals.class);

        Model model= gson.fromJson(protocolJson, modelType);
        return model;
    }


    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
