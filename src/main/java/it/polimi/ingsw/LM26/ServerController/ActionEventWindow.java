package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.io.Serializable;

public class ActionEventWindow implements Serializable{
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

}
