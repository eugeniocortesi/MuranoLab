package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

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

    Model model = singletonModel();
    PlayerZone player;
    Box[][] board;
    Random rand = new Random();
    DieInt die;
    int i;


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
        die=new Die(Color.ANSI_RED);
        die.roll();
    }

    @Test
    public void checkEffect(){

        board[0][0].setDie(die);
        assertFalse(model.getDecks().getToolCardDeck().get(8).play(die, board[0][0],player.getIDPlayer()));
        board[0][0].free();

        die=new Die(Color.ANSI_RED);
        die.roll();
        //first on the edge
        int i = rand.nextInt(4);
        int j = rand.nextInt(5);
        while (!model.getDecks().getToolCardDeck().get(8).play(die, board[i][j],player.getIDPlayer())) {
            i = rand.nextInt(4);
            j = rand.nextInt(5);
        }
        assertTrue(board[i][j].isIsPresent());
        if(board[i][j].getPatternBox().isShade())assertEquals(die.getValue(), board[i][j].getPatternBox().getValue());
        if(board[i][j].getPatternBox().isColor() && !board[i][j].getPatternBox().getColor().equals(Color.WHITE))
            assertEquals(die.getValue(), board[i][j].getPatternBox().getValue());
        assertTrue(i==0 || j==0 || i==3 || j==4);
        player.getPlayerBoard().printCard();

        player.getActionHistory().setDieUsed(false) ;
        player.getActionHistory().setPlacement(false);
        //second everywhere
        die=new Die(Color.ANSI_RED);
        die.roll();
        i = rand.nextInt(4);
        j = rand.nextInt(5);
        while (!model.getDecks().getToolCardDeck().get(8).play(die, board[i][j],player.getIDPlayer())) {
            i = rand.nextInt(4);
            j = rand.nextInt(5);
        }
        assertTrue(board[i][j].isIsPresent());
        if(board[i][j].getPatternBox().isShade())assertEquals(die.getValue(), board[i][j].getPatternBox().getValue());
        if(board[i][j].getPatternBox().isColor() && !board[i][j].getPatternBox().getColor().equals(Color.WHITE))
            assertEquals(die.getColor(), board[i][j].getPatternBox().getColor());
        player.getPlayerBoard().printCard();



        assertTrue(player.getActionHistory().isDieUsed() && player.getActionHistory().isPlacement());
    }


}