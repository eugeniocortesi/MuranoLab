package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.ServerController.VisitorInt;

public class EventMessage extends ClassMessage {

    private String idEvent;
    private ActionEvent actionEvent;

    public EventMessage(String idEvent, ActionEvent actionEvent) {
        this.idEvent = idEvent;
        this.actionEvent = actionEvent;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public ActionEvent getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
    }

    static public EventMessage deserializeEventMessage(String protocolJson){
        Gson gson = new Gson();
        EventMessage message= gson.fromJson(protocolJson, EventMessage.class);
        return message;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEvent(actionEvent);
    }
}
