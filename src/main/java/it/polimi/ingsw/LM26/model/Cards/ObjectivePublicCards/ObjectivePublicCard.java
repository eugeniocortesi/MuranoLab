package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards;

import it.polimi.ingsw.LM26.model.Cards.CardInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.Effect;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ObjectivePublicCard class
 * @author Eugenio Cortesi
 */

public class ObjectivePublicCard extends CardInt {

    private int points;

    private Effect effect = null;

    private int id;

    private boolean inUse = false;

    private transient static final Logger LOGGER = Logger.getLogger(ObjectivePrivateCard.class.getName());

    public ObjectivePublicCard() {
    }

    public ObjectivePublicCard(int id, Effect effect) {

        this.effect = effect;

        this.id = id;

        this.typeCard = "ObjectivePublicCard";

        LOGGER.setLevel(Level.ALL);
    }

    public ObjectivePublicCard(int id, int points, Effect effect) {

        this.points = points;

        this.id = id;

        this.effect = effect;

        this.typeCard = "ObjectivePublicCard";

        LOGGER.setLevel(Level.ALL);
    }


    /**
     * the methos gets for a player the points he gained from this objective
     * @param player owner of the board on which the method is asking the points for this effect
     * @return number of points
     */

    public int computePoints(PlayerZone player) {

        int p = effect.checkEffect(player.getPlayerBoard());

        int result = 0;

        if (points != 0) {

            for (int i = 0; i < points; i++)

                result = result + p;

            return result;
        } else return p;
    }

    public void printCard() {

        LOGGER.log(Level.INFO, " "+ getId()%10);

        if (getId() != 10) LOGGER.log(Level.INFO, " "+ getPoints());

        LOGGER.log(Level.INFO, getEffect());
    }

    public String getEffect() {

        return effect.getE();
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

    public int getPoints() {

        return points;
    }

    public Effect getRealEffect() {

        return effect;
    }

    @Override
    public void rewrite() {

        this.typeCard = "ObjectivePublicCard";
    }
}