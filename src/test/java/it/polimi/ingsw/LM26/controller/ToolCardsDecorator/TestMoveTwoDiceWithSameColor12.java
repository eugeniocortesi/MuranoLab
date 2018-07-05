package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestMoveTwoDiceWithSameColor12 {



    Model model = singletonModel();
    Random rand = new Random();
    PlayerZone player;
    Box[][] board;
    DieInt die1, die2, die3, die4, die5, rt;
    PlaceDie placement;
    ArrayList<Box> from = new ArrayList<>();
    ArrayList<Box> to = new ArrayList<>();
    int a, b, c, d;

    @Before
    public void setup() {
        model.reset();
        int i;
        int index = 0;
        for (i = 0; i < 2; i++)
            model.getPlayerList().add(new PlayerZone("name" + i, i));
        for (i = 0; i < 2; i++) {
            model.getPlayerList().get(i).setNumberPlayer(i);
            index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(index));
            model.getPlayerList().get(i).setPlayerBoard(model.getDecks().getWindowFramePlayerBoardDeck().get(i));
            model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        }
        player = model.getPlayerList().get(0);
        board = model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix();

    }

    @Test
    public void checkEffect() {

        int i, j, k, h;


        die1 = new Die(Color.ANSI_BLUE);
        die1.roll();
        die2 = new Die(Color.ANSI_RED);
        die2.roll();
        die3 = new Die(Color.ANSI_PURPLE);
        die3.roll();
        die4 = new Die(Color.ANSI_YELLOW);
        die4.roll();
        die5 = new Die(Color.ANSI_GREEN);
        die5.roll();

        player.getPlayerBoard().setNumDice(2);
        player.getPlayerBoard().printCard();
        from = new ArrayList<Box>();
        i = 1;
        j = 0;
        k = 1;
        h = 1;
        from.add(board[i][j]);
        from.add(board[k][h]);
        to = new ArrayList<Box>();
        while (i == 1 && j == 0) {
            i = rand.nextInt(4);
            j = rand.nextInt(5);
        }
        while (k == 1 && h == 1) {
            k = rand.nextInt(4);
            h = rand.nextInt(5);
        }
        to.add(board[i][j]);
        to.add(board[k][h]);

        rt=new Die(Color.ANSI_RED);
        rt.roll();

        if ((!from.get(0).isIsPresent() || !from.get(1).isIsPresent()) && (!to.get(0).isIsPresent() || !to.get(1).isIsPresent()))
            assertFalse(model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player));

        //dice are not same color as the one from roundTrack
        assertFalse(model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player));

        die1.setColor(Color.ANSI_RED);

        from = new ArrayList<Box>();
        i = 1;
        j = 0;
        k = 1;
        h = 1;
        board[i][j].setDie(die1);
        board[k][h].setDie(die2);
        board[2][2].setDie(die3);
        board[0][2].setDie(die4);
        board[3][3].setDie(die5);
        from.add(board[i][j]);
        from.add(board[k][h]);
        to = new ArrayList<Box>();
        while (i == 1 && j == 0) {
            i = rand.nextInt(4);
            j = rand.nextInt(5);
        }
        while (k == 1 && h == 1) {
            k = rand.nextInt(4);
            h = rand.nextInt(5);
        }
        to.add(board[i][j]);
        to.add(board[k][h]);

        int count = 0;
        while (count < 1000 && !model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player)) {
            player.getPlayerBoard().printCard();
            System.out.println("error");
            to = new ArrayList<Box>();
            i = rand.nextInt(4);
            j = rand.nextInt(5);
            k = rand.nextInt(4);
            h = rand.nextInt(5);
            while (i == 1 && j == 0) {
                i = rand.nextInt(4);
                j = rand.nextInt(5);
            }
            while (k == 1 && h == 1) {
                k = rand.nextInt(4);
                h = rand.nextInt(5);
            }
            to.add(board[i][j]);
            to.add(board[k][h]);

            count++;
        }
        player.getPlayerBoard().printCard();
        if (count < 1000) {
            System.out.println("done " + count);
            placement = new PlaceDie(to.get(0).getDie(), to.get(0), player);
            assertTrue(placement.checkNearByRestrictions());
            if (to.get(0).getPatternBox().isColor() && !to.get(0).getPatternBox().getColor().equals(Color.WHITE))
                assertEquals(to.get(0).getDie().getColor(), to.get(0).getPatternBox().getColor());
            if (to.get(0).getPatternBox().isShade())
                assertEquals(to.get(0).getDie().getValue(), to.get(0).getPatternBox().getValue());
            placement = new PlaceDie(to.get(1).getDie(), to.get(1), player);
            assertTrue(placement.checkNearByRestrictions());
            if (to.get(1).getPatternBox().isColor() && !to.get(1).getPatternBox().getColor().equals(Color.WHITE))
                assertEquals(to.get(1).getDie().getColor(), to.get(1).getPatternBox().getColor());
            if (to.get(1).getPatternBox().isShade())
                assertEquals(to.get(1).getDie().getValue(), to.get(1).getPatternBox().getValue());
        }
    }
}