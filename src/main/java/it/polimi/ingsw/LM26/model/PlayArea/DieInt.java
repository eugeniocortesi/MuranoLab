package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.Color;

public interface DieInt {

    public Color getColor();

    public void setColor(Color c);

    public void roll();

    public void dump();
}
