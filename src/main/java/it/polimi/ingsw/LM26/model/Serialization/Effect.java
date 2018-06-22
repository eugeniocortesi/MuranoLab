package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;

import java.io.Serializable;

public abstract class Effect implements Serializable {

     protected String typeEffect = "typeEffect";

     public abstract String getE();

     protected abstract void resolve();

     public abstract void rewrite();

     public abstract int checkEffect(WindowFramePlayerBoard b);

     public abstract int getPoints();

}
