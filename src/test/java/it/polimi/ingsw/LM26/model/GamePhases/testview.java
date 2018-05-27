package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;
import it.polimi.ingsw.LM26.view.cli.ConsoleTools;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;



public class testview {

    private Decks deck=singletonDecks();

    PlayerZone playerZone = new PlayerZone("n", 1);

    private ConsoleStrings consoleStrings= new ConsoleStrings();
    private ConsoleTools consoleTools = new ConsoleTools();

    @Before
    public void setup(){
        playerZone.setWindowPatternCard(deck.getWindowPatternCardDeck().get(0));
        playerZone.setPlayerBoard(deck.getWindowFramePlayerBoardDeck().get(0));
    }

    @Test
    public void frameboardtest(){
        consoleTools.printFrameBoard(playerZone);
    }

    @Test
    public void starttest(){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=consoleStrings.initialScreen();
        System.out.println(s);
    }

    @Test
    public void printobjpubdeck(){
        for(ObjectivePublicCard i : deck.getObjectivePublicCardDeck()){
            i.printCard();
        }
    }
}
