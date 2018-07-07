package it.polimi.ingsw.LM26.model.Cards;

import java.io.Serializable;

/**
 * Card abstract class
 * @author Eugenio Cortesi
 */

public abstract class CardInt implements Serializable {

    protected String typeCard;

    public abstract void rewrite();
}
