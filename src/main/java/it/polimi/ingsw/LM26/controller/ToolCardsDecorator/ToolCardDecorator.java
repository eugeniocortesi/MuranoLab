package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;

public abstract class ToolCardDecorator extends ToolCardInt {

    public String type = "ToolCardDecorator";


    /**
     * method that rewrite type for serializing with gson
     */

    public abstract void rewrite();

    public ToolCardDecorator() {
    }
}
