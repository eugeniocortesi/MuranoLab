package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
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

        int i, index;
        for (i = 0; i < 2; i++)
            model.getPlayerList().add(new PlayerZone("name" + i, i));
        for (i = 0; i < 2; i++) {
            model.getPlayerList().get(i).setNumberPlayer(i);
            index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(index));
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

        assertFalse(model.getDraftPool().getInDraft().contains(die));
        if(model.getBag().getInBag().contains(die))
            System.out.println("bag contains die ");
        assertTrue(model.getRestrictions().isFirstPart());
        System.out.println(model.getRestrictions().getDie().getColor());

        //a placement has been done
        model.getPlayerList().get(0).getActionHistory().setDieUsed(true);
        model.getPlayerList().get(0).getActionHistory().setPlacement(true);
        assertFalse(model.getDecks().getToolCardDeck().get(10).play(die, model.getPlayerList().get(0)));

        die = model.getRestrictions().getDie();
        die.setRoll(3);

        boolean ok=false;
        int count=0;

        while(!ok && count<1000) {

            int i = rand.nextInt(4);
            int j = rand.nextInt(5);

            if (model.getDecks().getToolCardDeck().get(10).play(3, board[i][j],player)) {
                player.getPlayerBoard().printCard();
                ok = true;
            } else {
                System.out.println("attempt "+ count+ " went wrong");
                assertEquals(model.getRestrictions().getDie().getColor(), model.getRestrictions().getColor());
                assertEquals(model.getRestrictions().getDie().getValue(), 3);
                assertTrue(model.getRestrictions().isNeedPlacement());
            }
            count++;
        }

        System.out.println(count);
        model.getRestrictions().setFirstPart(false);
        assertFalse(model.getDecks().getToolCardDeck().get(10).play(3, board[0][0], player));

    }

}