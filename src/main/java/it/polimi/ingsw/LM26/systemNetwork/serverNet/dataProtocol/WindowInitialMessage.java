package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;

public class WindowInitialMessage extends ClassMessage {

    private String meth;
    private int id;
    private ArrayList<WindowPatternCard> windowlist;

    public WindowInitialMessage(String op, int id, ArrayList<WindowPatternCard> four){
        this.meth = op;
        this.id = id;
        this.windowlist = four;
    }

    public String getMeth() {
        return meth;
    }

    public int getId() {
        return id;
    }

    public ArrayList<WindowPatternCard> getWindowlist() {
        return windowlist;
    }

    static public WindowInitialMessage deserializeDataMessage(String protocolJson){
        Gson gson = new Gson();
        WindowInitialMessage message= gson.fromJson(protocolJson, WindowInitialMessage.class);
        return message;
    }

    public void dump() {

        System.out.println("Operation " +this.meth+ " id " +this.id+ " windowlist " +this.windowlist);
    }
}
