package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;

public abstract class ToolCardDecorator extends ToolCardInt {

    public String type = "ToolCardDecorator";

    public abstract void rewrite();

    public ToolCardDecorator() {
    }
}
