package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class TestWindowPatternCard {
    Model model;
    Decks decks;

    @Test
    public void checkPatternCreation(){

        model= singletonModel();
        model.reset();
        decks=model.getDecks();
        int j= decks.getWindowPatternCardDeck().size();
        for(int i=0; i<j; i++)
        decks.getWindowPatternCardDeck().get(i).printCard();

    }

}