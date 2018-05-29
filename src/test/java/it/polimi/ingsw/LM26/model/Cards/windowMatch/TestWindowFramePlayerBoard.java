package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class TestWindowFramePlayerBoard {

    private Decks decks = singletonDecks();

    @Test
    public void checkInsert(){

        decks.getWindowFramePlayerBoardDeck().get(1).insertPatternIntoBoard(decks.getWindowPatternCardDeck().get(1).getWindowPatter());
        decks.getWindowFramePlayerBoardDeck().get(1).printCard();

    }

}