package it.polimi.ingsw.LM26.modelView;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.ServerController.VisitorInt;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

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

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    static public ModelMessage deserializeModelMessage(String protocolJson){
        Gson gson = new Gson();
        ModelMessage message= gson.fromJson(protocolJson, ModelMessage.class);
        return message;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
