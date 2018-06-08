package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.ServerController.VisitorInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

public class WindowAnswerMessage extends ClassMessage {

    private String cod;

    private ActionEventWindow actionEventWindow;

    public WindowAnswerMessage(String cod, ActionEventWindow actionEventWindow){
        this.cod = cod;
        this.actionEventWindow = actionEventWindow;
    }

    public String getCod() {
        return cod;
    }

    public ActionEventWindow getActionEventWindow(){ return actionEventWindow;
    }

    static public WindowAnswerMessage deserializeWindowAnswerMessage(String protocolJson){
        Gson gson = new Gson();
        WindowAnswerMessage message= gson.fromJson(protocolJson, WindowAnswerMessage.class);
        return message;
    }

    public void dump() {

        System.out.println("Operation " +this.cod+ " name " +this.actionEventWindow.getName()+ " card " + this.actionEventWindow.getWindowPatternCard());
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitWindowAnswerMessage(this);
    }
}
