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

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestMoveWithNoValueRestriction3 {

    Model model;
    Random rand = new Random();
    PlayerZone player;
    Box[][] board;
    DieInt die1;
    DieInt die2;
    PlaceDie placement;

    @Before
    public void setup() {

        model= singletonModel();
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
        player=model.getPlayerList().get(0);
        board=model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix();

    }

    @Test
    public void checkEffect() {

        player.getPlayerBoard().printCard();

        DieInt die = new Die(Color.ANSI_RED);
        die.setRoll(4);

        PlaceDie   placement = new PlaceDie(die,board[0][0], player);
        assertTrue(placement.placeDie());

        player.getPlayerBoard().incrementNumDice();

        player.getPlayerBoard().printCard();

        assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[0][0], board[1][1], player));

        assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[0][0], board[2][2], player));

        assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[0][0], board[1][2], player));

        assertTrue(model.getDecks().getToolCardDeck().get(2).play(board[0][0], board[3][1], player));

        player.getPlayerBoard().printCard();

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);

        placement = new PlaceDie(die, board[1][2], player);
        placement.placeDie();
        player.getPlayerBoard().incrementNumDice();

        die = new Die(Color.ANSI_PURPLE);
        die.setRoll(5);

        placement = new PlaceDie(die, board[2][2], player);
        placement.placeDie();
        player.getPlayerBoard().incrementNumDice();

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(2);

        placement = new PlaceDie(die, board[1][2], player);
        placement.placeDie();
        player.getPlayerBoard().incrementNumDice();

        player.getPlayerBoard().printCard();

        assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[2][2], board[3][4], player));

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(1);

        placement = new PlaceDie(die, board[3][2], player);
        placement.placeDie();
        player.getPlayerBoard().incrementNumDice();

        player.getPlayerBoard().printCard();

        assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[3][2], board[1][1], player));

        player.getPlayerBoard().printCard();

        board[1][2].free();

        assertTrue(model.getDecks().getToolCardDeck().get(2).play(board[3][2], board[1][1], player));

        player.getPlayerBoard().printCard();



    }
}

