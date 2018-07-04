package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;

/**
 * ActionEventWindow class
 * @author Chiara Criscuolo
 * Contains the windowPatternCard chosen by the player "name"
 */

public class ActionEventWindow extends ClassMessage implements Serializable{

    private String name;

    private WindowPatternCard windowPatternCard;

    /**
     * Constructor
     * @param name username of the player
     * @param windowPatternCard chosen by the player
     */

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

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEventWindow(this);
    }
}
