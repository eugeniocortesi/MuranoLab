package it.polimi.ingsw.LM26.Serialization;
import org.junit.Test;
import it.polimi.ingsw.LM26.Cards.Decks;

import static it.polimi.ingsw.LM26.Serialization.SingletonDecks.singletonDecks;
import static org.junit.Assert.*;

public class TestSingletonDecks {

    Decks decks;

    @Test

    public void checkSingleton(){

        int i;

        if(decks==null)System.out.println("ok, mazzo ancora da creare");
        decks=singletonDecks();
        if(decks!=null)System.out.println("ok");

        for(i=0; i<=4; i++) {
            decks.getObjectivePrivateCardDeck().get(i).printCard();
            }

        for(i=0; i<=9; i++) {
            decks.getObjectivePublicCardDeck().get(i).printCard();
        }

        for(i=0; i<=23; i++) {
            decks.getWindowPatternCardDeck().get(i).printCard();
        }

        for(i=0; i<=3; i++) {
            decks.getWindowFramePlayerBoardDeck().get(i).printCard();
        }
        for(i=0; i<=11; i++) {
            decks.getToolCardDeck().get(i).printCard();
        }

    }

}