package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
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

public class TestPlaceDie {

    private Model model;

    private Random rand = new Random();

    private Box[][] b=null;

    private PlayerZone player;

    private int i;

    private int attempt=0;

    private int dieCount=0;

    private int[][] values=new int[4][5];


    @Before

    public void setup() {

        model= singletonModel();
        model.reset();

        model.getPlayerList().add(new PlayerZone("name" + i, i));

        model.getPlayerList().get(i).setNumberPlayer(i);

        WindowPatternCard pattern = model.getDecks().getWindowPatternCardDeck().get(0);

        model.getPlayerList().get(i).setWindowPatternCard(pattern);

        WindowFramePlayerBoard board=model.getDecks().getWindowFramePlayerBoardDeck().get(0);

        model.getPlayerList().get(i).setPlayerBoard(board);

        model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());

        b=model.getPlayerList().get(i).getPlayerBoard().getBoardMatrix();

        player=model.getPlayerList().get(i);

        player.getPlayerBoard().printCard();
    }

    @Test

    public void checkPlacement(){



        DieInt die = new Die(Color.ANSI_RED);
        die.setRoll(4);

        //Board is empty
        PlaceDie placement = new PlaceDie(die,b[2][2], player);
        assertFalse(placement.placeDie()); // must be on edge

        placement = new PlaceDie(die,b[3][0], player);
        assertFalse(placement.placeDie()); //error in color restriction

        placement = new PlaceDie(die,b[3][1], player);
        assertFalse(placement.placeDie()); //error in value restriction

        placement = new PlaceDie(die,b[0][0], player);
        assertTrue(placement.placeDie());

        player.getPlayerBoard().printCard();

        die = new Die(Color.ANSI_RED);
        die.setRoll(5);

        // not first die must be near another die

        placement = new PlaceDie(die,b[0][2], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[0][3], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[0][4], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[1][4], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[2][0], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[2][4], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[3][0], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[3][4], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[2][2], player);
        assertFalse(placement.placeDie());

        placement = new PlaceDie(die,b[1][0], player);
        assertFalse(placement.placeDie()); // cant be near a die of same color

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        placement = new PlaceDie(die,b[0][1], player);
        assertFalse(placement.placeDie());  // cant be nea a die o same value

        System.out.print(player.getPlayerBoard().getNumDice());
        System.out.print(player.getPlayerBoard().isEmpty());

        die.setRoll(4);
        placement = new PlaceDie(die,b[1][1], player);
        assertTrue(placement.placeDie());

        player.getPlayerBoard().printCard();

        die = new Die(Color.ANSI_GREEN);
        die.setRoll(2);
        placement = new PlaceDie(die,b[1][2], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_GREEN);
        die.setRoll(4);
        placement = new PlaceDie(die,b[1][1], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_RED);
        die.setRoll(2);
        placement = new PlaceDie(die,b[1][3], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_GREEN);
        die.setRoll(2);
        placement = new PlaceDie(die,b[0][2], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_GREEN);
        die.setRoll(5);
        placement = new PlaceDie(die,b[3][3], player);
        assertFalse(placement.placeDie());

        player.getPlayerBoard().printCard();

        die = new Die(Color.ANSI_RED);
        die.setRoll(2);
        placement = new PlaceDie(die,b[2][1], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_GREEN);
        die.setRoll(2);
        placement = new PlaceDie(die,b[3][0], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(2);
        placement = new PlaceDie(die,b[2][3], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_PURPLE);
        die.setRoll(2);
        placement = new PlaceDie(die,b[3][4], player);
        assertTrue(placement.placeDie());

        player.getPlayerBoard().printCard();

        die = new Die(Color.ANSI_PURPLE);
        die.setRoll(2);
        placement = new PlaceDie(die,b[1][0], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_RED);
        die.setRoll(6);
        placement = new PlaceDie(die,b[1][3], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(6);
        placement = new PlaceDie(die,b[0][4], player);
        assertTrue(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(6);
        placement = new PlaceDie(die,b[1][4], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(2);
        placement = new PlaceDie(die,b[1][4], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);
        placement = new PlaceDie(die,b[1][4], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);
        placement = new PlaceDie(die,b[0][3], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(6);
        placement = new PlaceDie(die,b[0][3], player);
        assertFalse(placement.placeDie());

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(4);
        placement = new PlaceDie(die,b[0][3], player);
        assertFalse(placement.placeDie());

        player.getPlayerBoard().printCard();

        b[0][0].free();

        b[1][0].free();

        die = new Die(Color.ANSI_YELLOW);
        die.setRoll(1);
        placement = new PlaceDie(die,b[0][0], player);
        assertTrue(placement.placeDie());

        player.getPlayerBoard().printCard();


    }


}