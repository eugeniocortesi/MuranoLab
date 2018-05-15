package it.polimi.ingsw.LM26.model.Serialization;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class TestSingletonDecks {

    private Decks decks;

    @Test

    public void checkSingleton(){

        int i;

        if(decks==null)System.out.println("ok, deck still to be created");
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
            PlayerZone player =new PlayerZone("Jack", 1);
            decks.getToolCardDeck().get(i).play(player);
        }

    }

}