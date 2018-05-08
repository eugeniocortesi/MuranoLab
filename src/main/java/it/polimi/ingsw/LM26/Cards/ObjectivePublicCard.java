package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Effect;

import java.util.ArrayList;

public class ObjectivePublicCard extends ObjectiveCardAC {

    public int points;

    public Effect effect= null;

    int id;

    public ObjectivePublicCard(int id, Effect effect) {

        this.effect=effect;
        this.id=id;
    }

    public ObjectivePublicCard(int id, int points, Effect effect) {

        this.points = points;
        this.id=id;
        this.effect = effect;

    }

    public int getPoints() {

        return points;
    }

    public String getEffect() {

        return  effect.getE();
    }

    public int getId() {
        return id;
    }
}
