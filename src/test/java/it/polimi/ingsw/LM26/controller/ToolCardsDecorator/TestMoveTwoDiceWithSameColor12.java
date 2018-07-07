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
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
            model.getPlayerList().get(i).setPlayerBoard(model.getDecks().getWindowFramePlayerBoardDeck().get(i));
            model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        }
        player = model.getPlayerList().get(0);
        board = model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix();

    }

    @Test
    public void checkEffect() {

        player.getPlayerBoard().printCard();

        DieInt rt = new Die(Color.ANSI_RED);
        rt.setRoll(4);

        DieInt die = new Die(Color.ANSI_RED);
        die.setRoll(4);

        PlaceDie   placement = new PlaceDie(die,board[0][0], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);

        placement = new PlaceDie(die, board[0][1], player);
        placement.placeDie();


        die = new Die(Color.ANSI_RED);
        die.setRoll(6);

        placement = new PlaceDie(die, board[1][2], player);
        placement.placeDie();

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(4);

        placement = new PlaceDie(die, board[2][3], player);
        placement.placeDie();

        die = new Die(Color.ANSI_RED);
        die.setRoll(6);

        placement = new PlaceDie(die, board[2][4], player);
        placement.placeDie();

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(5);

        placement = new PlaceDie(die, board[2][2], player);
        placement.placeDie();

        player.getPlayerBoard().printCard();


        from = new ArrayList<Box>();

        to = new ArrayList<Box>();

        from.add(board[0][0]);

        from.add(board[2][3]);

        to.add(board[3][2]);

        to.add(board[2][1]);

        assertFalse(model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player));

        player.getPlayerBoard().printCard();

        from.removeAll(from);

        to.removeAll(to);

        from.add(board[0][0]);

        from.add(board[2][4]);

        to.add(board[1][1]);

        to.add(board[1][4]);

        assertFalse(model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player));

        player.getPlayerBoard().printCard();

        from.removeAll(from);

        to.removeAll(to);

        from.add(board[0][0]);

        from.add(board[2][4]);

        to.add(board[3][2]);

        to.add(board[1][4]);

        assertTrue(model.getDecks().getToolCardDeck().get(11).play(rt, from, to, player));

        player.getPlayerBoard().printCard();
    }
}