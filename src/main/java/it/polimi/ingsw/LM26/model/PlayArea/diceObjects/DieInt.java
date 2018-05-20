package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

public interface DieInt {

    public Color getColor();

    public void setColor(Color c);

    public void roll();

    public void dump();

    public int getValue();
}
