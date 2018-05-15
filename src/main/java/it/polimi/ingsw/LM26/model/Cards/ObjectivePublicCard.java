package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Effect;

public class ObjectivePublicCard extends ObjectiveCardAC {

    private int points;

    private Effect effect= null;

    private int id;

    private boolean inUse= false;

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

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getId() {
        return id;
    }

    public void printCard(){

        System.out.println(getId());
        if(getId()!=10) System.out.println(getPoints());
        System.out.println(getEffect());

    }
}
