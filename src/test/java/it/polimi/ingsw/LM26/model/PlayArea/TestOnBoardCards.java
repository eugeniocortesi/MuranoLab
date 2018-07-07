package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
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

        four=model.getOnBoardCards().giveFourWindowPattern();

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

        for(int i = 0; i<model.getDecks().getObjectivePublicCardDeck().size(); i++)

            model.getDecks().getObjectivePublicCardDeck().get(i).setInUse(false);

        model.getDecks().getObjectivePublicCardDeck().get(0).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(1).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(2).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(3).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(4).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(5).setInUse(true);
        model.getDecks().getObjectivePublicCardDeck().get(6).setInUse(true);

        model.getOnBoardCards().getObjectivePublicCardList().removeAll(model.getOnBoardCards().getObjectivePublicCardList());
        model.getOnBoardCards().setPublicCards();

        System.out.print("\n");

        for( int i=0; i<model.getOnBoardCards().getObjectivePublicCardList().size(); i++)
            System.out.print( model.getOnBoardCards().getObjectivePublicCardList().get(i).getId()+" ");


        for(int i=0; i<four.size();i++) {

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(2));

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(4));

            assertNotSame(four.get(i), model.getDecks().getWindowPatternCardDeck().get(8));

            for(int j=0; j<four.size(); j++)

                if(j!=i) assertNotSame(four.get(i), four.get(j));
        }


    }
}