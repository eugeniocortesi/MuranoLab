package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;

/**
 * WindowAnswerMessage class
 * @author Chiara Criscuolo
 * It contains the actionEventWindow
 */

public class WindowAnswerMessage extends ClassMessage {

    private String cod;

    private ActionEventWindow actionEventWindow;

    public WindowAnswerMessage(String cod, ActionEventWindow actionEventWindow){
        this.cod = cod;
        this.actionEventWindow = actionEventWindow;
    }

    public ActionEventWindow getActionEventWindow(){ return actionEventWindow;
    }

    /**
     * Method that return from a string with json the WindowAnswerMessage
     * @param protocolJson string to deserialize
     * @return WindowAnswerMessage
     */

    static public WindowAnswerMessage deserializeWindowAnswerMessage(String protocolJson){
        Gson gson = new Gson();
        return gson.fromJson(protocolJson, WindowAnswerMessage.class);
    }

    /**
     * Shortcut to print it
     */

    public void dump() {

        System.out.println("Operation " +this.cod+ " name " +this.actionEventWindow.getName()+ " card " + this.actionEventWindow.getWindowPatternCard());
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitWindowAnswerMessage(this);
    }
}
