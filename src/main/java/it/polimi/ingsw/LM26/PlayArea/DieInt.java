package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.Color;

public interface DieInt {

    public Color getColor();

    public void setColor(Color c);

    public void roll();

    public void dump();
}
