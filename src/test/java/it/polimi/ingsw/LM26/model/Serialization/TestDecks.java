package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestDecks {

    Decks decks;

    @Before
    public void setup() {
        decks = new Decks();

        try {
            decks.setup();
        } catch (IOException e) {
        }

        decks.serialize();
    }

    @Test
    public void check(){

        System.out.println("Public cards");
        for(int i=0; i<decks.getObjectivePublicCardDeck().size(); i++)
            decks.getObjectivePublicCardDeck().get(i).printCard();

        System.out.println("Private cards");
        for(int i=0; i<decks.getObjectivePrivateCardDeck().size(); i++)
            decks.getObjectivePrivateCardDeck().get(i).printCard();

        System.out.print("Window patter");
        for(int i=0; i<decks.getWindowPatternCardDeck().size(); i++)
            decks.getWindowPatternCardDeck().get(i).printCard();
        System.out.println(decks.getWindowPatternCardDeck().size());
    }

}