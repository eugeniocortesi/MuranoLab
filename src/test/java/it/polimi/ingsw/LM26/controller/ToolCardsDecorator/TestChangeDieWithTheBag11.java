package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestChangeDieWithTheBag11 {

    Model model = singletonModel();
    PlayerZone player;
    Box[][] board;
    Random rand = new Random();
    DieInt die;

    @Before
    public void setup(){

        model.reset();

        int i, index;
        for (i = 0; i < 2; i++)
            model.getPlayerList().add(new PlayerZone("name" + i, i));
        for (i = 0; i < 2; i++) {
            model.getPlayerList().get(i).setNumberPlayer(i);
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
            model.getPlayerList().get(i).setPlayerBoard(model.getDecks().getWindowFramePlayerBoardDeck().get(i));
            model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        }
        player=model.getPlayerList().get(0);
        board=model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix();

        for( i=0; i<5;i++){
           die =model.getBag().draw();
            die.roll();
            model.getDraftPool().addDie(die);
        }
        model.getDraftPool().printDraftPool();
    }

    @Test
    public void checkFirstPart(){

        DieInt die = model.getDraftPool().get(3);

        //no placement has been done
        assertTrue(model.getDecks().getToolCardDeck().get(10).play(die, model.getPlayerList().get(0)));

        assertTrue((model.getRestrictions().isFirstPart()));

        assertTrue(model.getBag().getInBag().contains(die));

        System.out.println(model.getRestrictions().getDie().getColor());

        model.getDraftPool().printDraftPool();



       assertEquals(model.getDraftPool().getInDraft().size(), 4);

       assertFalse(model.getDraftPool().getInDraft().contains(die));

        assertFalse(model.getDecks().getToolCardDeck().get(10).play(10, board[0][0], player));

        assertFalse(model.getDecks().getToolCardDeck().get(10).play(4, board[1][1], player));

        assertTrue(model.getDraftPool().getInDraft().contains(model.getRestrictions().getDie()));

        assertTrue(model.getRestrictions().isNeedPlacement());

        assertEquals(model.getDraftPool().getInDraft().size(), 5);

        assertTrue(model.getDecks().getToolCardDeck().get(10).play(4, board[1][0], player));

        model.getPlayerList().get(0).getActionHistory().setDieUsed(true);
        model.getPlayerList().get(0).getActionHistory().setPlacement(true);





    }

}