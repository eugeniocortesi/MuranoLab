package it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

public class WindowAnswerMessage extends ClassMessage {

    private String cod;

    private WindowPatternCard windowcard;

    public WindowAnswerMessage(String cod, WindowPatternCard windowPatternCard){
        this.cod = cod;
        windowcard = windowPatternCard;
    }

    public String getCod() {
        return cod;
    }

    public WindowPatternCard getWindowcard() {
        return windowcard;
    }

    static public WindowAnswerMessage deserializeDataMessage(String protocolJson){
        Gson gson = new Gson();
        WindowAnswerMessage message= gson.fromJson(protocolJson, WindowAnswerMessage.class);
        return message;
    }

    public void dump() {

        System.out.println("Operation " +this.cod+ "card " +this.windowcard);
    }
}
