package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

public interface DieInt extends Serializable{

    public Color getColor();

    public void setColor(Color c);

    public void roll();

    public void dump();

    public int getValue();

    public void increment();

    public void decrement();

    public void setRoll(int value);
}
