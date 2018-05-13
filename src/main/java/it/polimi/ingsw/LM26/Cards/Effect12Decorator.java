package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect12Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect12Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {







    }
}
