package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

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
            index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(index));
            model.getPlayerList().get(i).setPlayerBoard(model.getDecks().getWindowFramePlayerBoardDeck().get(i));
            model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        }
        player=model.getPlayerList().get(0);
        board=model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix();

    }

    @Test
    public void checkEffect() {

       /* int i= 0, j= 0 ;
        i = rand.nextInt(4);
        j = rand.nextInt(5);

        if(!board[i][j].isIsPresent()) assertFalse(model.getDecks().getToolCardDeck().get(2).play(board[i][j], board[0][0], player.getIDPlayer()));
        //die not present in first position

        die1 = new Die(Color.ANSI_BLUE);
        die1.roll();
        board[0][0].setDie(die1);
        player.getPlayerBoard().setNumDice(1);
        player.getPlayerBoard().printCard();

        //if it is the only die on the board, it has to be placed on the edge again
        i = rand.nextInt(4);
        j = rand.nextInt(5);
        System.out.println("trying with "+i+" "+j);
        while(!model.getDecks().getToolCardDeck().get(2).play(board[0][0], board[i][j], player.getIDPlayer())) {
            i = rand.nextInt(4);
            j = rand.nextInt(5);
            System.out.println("trying with "+i+" "+j);
        }
        player.getPlayerBoard().printCard();
        if(board[i][j].getPatternBox().isColor() && !board[i][j].getPatternBox().getColor().equals(Color.WHITE))
            assertEquals(die1.getColor(), board[i][j].getPatternBox().getColor());
        placement = new PlaceDie(die1, board[i][j], player);
        assertTrue(placement.checkEdgeRestrictions());


        board[i][j].free();
        board[1][0].setDie(die1);
        die2=new Die(Color.ANSI_RED);
        die2.roll();
        board[1][1].setDie(die2);
        player.getPlayerBoard().setNumDice(2);
        //die must respect nearby restriction and value, but can be placed on every colors
        i = rand.nextInt(4);
        j = rand.nextInt(5);
        while(! model.getDecks().getToolCardDeck().get(2).play(board[1][1], board[i][j], player.getIDPlayer())){
            i = rand.nextInt(4);
            j = rand.nextInt(5);
            System.out.println("trying with "+i+" "+j);
        }
        player.getPlayerBoard().printCard();
        //die must respect nearby restriction and value, but can be placed on every colors
        if(board[i][j].getPatternBox().isColor() && !board[i][j].getPatternBox().getColor().equals(Color.WHITE))
            assertEquals(die2.getColor(), board[i][j].getPatternBox().getColor());
        placement = new PlaceDie(die2, board[i][j], player);
        assertTrue(placement.checkNearByRestrictions());*/
    }
}

