package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestOnBoardCards {

        Model model= singletonModel();

        ArrayList<WindowPatternCard> four;

    @Test

    public void checkCardSelections(){

        model.reset();

        model.getDecks().getWindowPatternCardDeck().get(2).setInUse(true);

        model.getDecks().getWindowPatternCardDeck().get(4).setInUse(true);

        model.getDecks().getWindowPatternCardDeck().get(8).setInUse(true);

        four=model.getOnBoardCards().getFourWindowPattern();

        assertEquals(four.size(), 4);

        assertEquals(model.getOnBoardCards().getToolCardList().size(), 3);

        assertEquals(model.getOnBoardCards().getObjectivePublicCardList().size(), 3);

        assertEquals(model.getOnBoardCards().getToolArrayList().size(), 3);

        for(int i=0; i<model.getOnBoardCards().getToolCardList().size(); i++) {

            int n = model.getOnBoardCards().getToolArrayList().get(i);

            assertEquals(n, model.getOnBoardCards().getToolCardList().get(i).getNum());

            for(int j=0; j<model.getOnBoardCards().getToolCardList().size(); j++)

                if(j!=i) assertNotSame(model.getOnBoardCards().getToolCardList().get(i), model.getOnBoardCards().getToolCardList().get(j));
        }

        for(int i=0; i<model.getOnBoardCards().getObjectivePublicCardList().size(); i++)

            for(int j=0; j<model.getOnBoardCards().getObjectivePublicCardList().size(); j++)

                if(j!=i) assertNotSame(model.getOnBoardCards().getObjectivePublicCardList().get(i), model.getOnBoardCards().getObjectivePublicCardList().get(j));

        for(int i=0; i<four.size();i++) {

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(2));

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(4));

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(8));

            for(int j=0; j<four.size(); j++)

                if(j!=i) assertNotSame(four.get(i), four.get(j));
        }
    }
}