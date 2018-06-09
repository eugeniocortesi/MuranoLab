package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;

public class ActionEventWindow extends ClassMessage implements Serializable{
    private String name;
    private WindowPatternCard windowPatternCard;

    public ActionEventWindow(String name, WindowPatternCard windowPatternCard) {
        this.name = name;
        this.windowPatternCard = windowPatternCard;
    }

    public String getName() {
        return name;
    }


    public WindowPatternCard getWindowPatternCard() {
        return windowPatternCard;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEventWindow(this);
    }
}
