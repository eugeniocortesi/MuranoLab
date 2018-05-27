package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.view.ConsoleStrings;
import it.polimi.ingsw.LM26.view.View;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;



public class testview {

    private Decks deck=singletonDecks();

    PlayerZone playerZone = new PlayerZone("n", 1);

    private ConsoleStrings consoleStrings= new ConsoleStrings();

    @Before
    public void setup(){
        playerZone.setWindowPatternCard(deck.getWindowPatternCardDeck().get(0));
        playerZone.setPlayerBoard(deck.getWindowFramePlayerBoardDeck().get(0));
    }

    @Test
    public void frameboardtest(){
        consoleStrings.printFrameBoard(playerZone);
    }
}
