package it.polimi.ingsw.LM26.Cards;

import java.util.ArrayList;
import it.polimi.ingsw.LM26.Serialization.Elements.elements;

public class ObjectivePrivateCard extends ObjectiveCardAC {

    ArrayList<CardInt> PrivateDeck;

    public elements colour= null;

    public ObjectivePrivateCard(elements colour) {

        this.colour=colour;
    }
    public elements getColour() {

        return colour;
    }

    public ArrayList<CardInt> getPrivateDeck() {
        return PrivateDeck;
    }

    public void setPrivateDeck(ArrayList<CardInt> aPrivate) {
        PrivateDeck = aPrivate;
    }
}
