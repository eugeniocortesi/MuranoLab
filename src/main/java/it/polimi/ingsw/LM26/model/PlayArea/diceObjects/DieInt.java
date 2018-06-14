package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

public abstract class DieInt implements Serializable{

    String type;

    public abstract Color getColor();

    public abstract void setColor(Color c);

    public abstract int getNumber ();

    public abstract void roll();

    public abstract void dump();

    public abstract int getValue();

    public abstract void increment();

    public abstract void decrement();

    public abstract void setRoll(int value);

    public abstract void rewrite();
}
