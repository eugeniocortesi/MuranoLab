package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;

import java.io.Serializable;

public abstract class Effect implements Serializable {

     String typeEffect = "typeEffect";

     public abstract String getE();

     public abstract void rewrite();

     public abstract int checkEffect(WindowFramePlayerBoard b);
}
