package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.observers.serverController.VisitorInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;

/**
 * WindowInitialMessage
 * @author Chiara Criscuolo
 * It contains username and id of the player and a list of WindowPatternCard
 */

public class WindowInitialMessage extends ClassMessage {

    private String meth;

    private String user;

    private int id;

    private ArrayList<WindowPatternCard> windowlist;

    public WindowInitialMessage(String op, String s, int id, ArrayList<WindowPatternCard> four){

        this.meth = op;

        this.user= s;

        this.id = id;

        this.windowlist = four;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public ArrayList<WindowPatternCard> getWindowlist() {
        return windowlist;
    }

    /**
     * Method that return from a string with json the WindowInitialMessage
     * @param protocolJson string to deserialize
     * @return WindowInitialMessage
     */

    static public WindowInitialMessage deserializeWindowInitialMessage(String protocolJson){
        Gson gson = new Gson();
        return gson.fromJson(protocolJson, WindowInitialMessage.class);
    }

    /**
     * Shortcut to print it
     */

    public void dump() {

        System.out.println("Operation " +this.meth+ " user "+ user+" id " +this.id+ " windowlist " +this.windowlist);
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitWindowInitialMessage(this);
    }
}
