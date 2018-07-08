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
import static org.junit.Assert.*;

public class TestPlaceWithNotInProximities9 {

    Model model;
    PlayerZone player;
    Box[][] board;
    Random rand = new Random();
    DieInt die;
    int i;


    @Before

    public void setup(){

        model= singletonModel();
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

    }

    @Test
    public void checkEffect() {

        player.getPlayerBoard().printCard();

        die=new Die(Color.ANSI_RED);
        die.setRoll(6);

        //board is empty
        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[2][2], player));
        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[3][0], player));
        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[3][1], player));

        assertTrue(model.getDecks().getToolCardDeck().get(8).play(die, board[0][0], player));

        player.getPlayerBoard().printCard();


        die=new Die(Color.ANSI_RED);
        die.setRoll(6);

        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[2][2], player));
        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[3][0], player));

        player.getActionHistory().deleteRoundHistory();

        assertTrue(model.getDecks().getToolCardDeck().get(8).play(die, board[1][2], player));

        player.getPlayerBoard().printCard();

        player.getActionHistory().deleteRoundHistory();

        die=new Die(Color.ANSI_RED);
        die.setRoll(6);

        assertTrue(model.getDecks().getToolCardDeck().get(8).play(die, board[1][4], player));

        player.getPlayerBoard().printCard();

        die=new Die(Color.ANSI_GREEN);
        die.setRoll(2);

        player.getActionHistory().deleteRoundHistory();

        board[1][2].free();

        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[0][4], player));

        assertTrue(model.getDecks().getToolCardDeck().get(8).play(die, board[0][2], player));

        die=new Die(Color.ANSI_GREEN);
        die.setRoll(4);

        board[0][0].free();

        player.getPlayerBoard().printCard();

        player.getActionHistory().deleteRoundHistory();


        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[1][1], player));

        die=new Die(Color.ANSI_GREEN);
        die.setRoll(4);

        board[1][0].setDie(die);

        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[0][0], player));




    }
}