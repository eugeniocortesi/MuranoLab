package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.model.Serialization.SingletonDecks;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static org.junit.Assert.*;

public class TestWindowPatternCard {

    private Decks decks= singletonDecks();

    @Test
    public void checkPatternCreation(){
        int j= decks.getWindowPatternCardDeck().size();
        for(int i=0; i<j; i++)
        decks.getWindowPatternCardDeck().get(i).printCard();

    }

}