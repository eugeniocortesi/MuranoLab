package it.polimi.ingsw.LM26.Cards;

import org.junit.Test;

import static it.polimi.ingsw.LM26.Serialization.SingletonDecks.singletonDecks;
import static org.junit.Assert.*;

public class TestWindowFramePlayerBoard {

    private Decks decks = singletonDecks();

    @Test
    public void checkInsert(){

        decks.getWindowFramePlayerBoardDeck().get(1).insertPatternIntoBoard(decks.getWindowPatternCardDeck().get(1).getWindowPatter());
        decks.getWindowFramePlayerBoardDeck().get(1).printCard();

    }

}