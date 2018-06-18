package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;

public abstract class ToolCardDecorator extends ToolCardInt {

    public String type = "ToolCardDecorator";

    public abstract void rewrite();

    public ToolCardDecorator() {
    }
}
