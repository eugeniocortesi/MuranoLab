package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Effect;

import java.util.ArrayList;

public class ObjectivePublicCard extends ObjectiveCardAC {

    public int points;

    ArrayList<CardInt> PublicDeck;

    public Effect effect= null;

    public ObjectivePublicCard(Effect effect) {

        this.effect=effect;
    }

    public ObjectivePublicCard(int points, Effect effect) {

        this.points = points;

        this.effect = effect;

    }

    public int getPoints() {

        return points;
    }

    public String getEffect() {

        return  effect.getE();
    }

    public ArrayList<CardInt> getPublicDeck() {

        return PublicDeck;
    }

    public void setPublicDeck(ArrayList<CardInt> aPublic) {

        PublicDeck = aPublic;
    }
}
