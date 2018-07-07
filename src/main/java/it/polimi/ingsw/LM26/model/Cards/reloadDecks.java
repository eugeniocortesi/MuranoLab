package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.io.Serializable;

/**
 * @author Eugenio Cortesi
 * Class that calls the method that reads the cards from file
 * to make is more clean the creation of the Decks it's allocated in this class, instead of in the one
 * that deserializes and stores them.
 */

public class reloadDecks implements Serializable {

    public static Decks loadDecks() {

        Decks decks;

        decks = new Decks();

        decks.setup();

        decks.serialize();

        return decks;
    }
}
