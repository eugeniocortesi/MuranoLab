package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Effect;

public class ObjectivePublicCard extends CardInt {

    private int points;

    private Effect effect= null;

    private int id;

    private boolean inUse= false;

    public ObjectivePublicCard() {
    }

    public ObjectivePublicCard(int id, Effect effect) {

        this.effect=effect;
        this.id=id;
        this.typeCard = "ObjectivePublicCard";
    }

    public ObjectivePublicCard(int id, int points, Effect effect) {

        this.points = points;
        this.id=id;
        this.effect = effect;
        this.typeCard = "ObjectivePublicCard";

    }

    public int getPoints() {
        return points;
    }

    public int computePoints(PlayerZone player) {

        int p = effect.checkEffect(player.getPlayerBoard());
        int result=0;
        if(points!=0) {
            for(int i=0; i<points;i++)
                result=result+p;
            return result;
        }
        else return p;
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

    @Override
    public void rewrite() {

        this.typeCard = "ObjectivePublicCard";

    }

    public Effect getRealEffect() {
        return effect;
    }
}
