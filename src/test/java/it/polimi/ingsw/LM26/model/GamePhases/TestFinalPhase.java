package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.controller.GamePhases.FinalPhase;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestFinalPhase {

    private ArrayList<PlayerZone> playerList= new ArrayList<PlayerZone>();
    private Random rand = new Random();
    private final int roundTrackSize = 100;
    private int random;
    private PlayerZone player;

    @Before
    public void FinalPhaseSetUp(){
        for(int i=0; i<4; i++){
            playerList.add(new PlayerZone("name", i+1));
        }

    }

    @Test
    public void testDeclareWinnerRandom(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++){
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            random=rand.nextInt(roundTrackSize);
            System.out.println(random);
            playerList.get(i).getScoreMarker().incrementScore(random);
        }
        System.out.println("\n");
        for(int i=0; i<playerList.size(); i++){
            random=rand.nextInt(roundTrackSize);
            System.out.println(random);
            playerList.get(i).setPrivatePoints(random);
        }
        System.out.println("\n");
        for(int i=0; i<playerList.size(); i++){
            random=rand.nextInt(3);
            System.out.println(random);
            playerList.get(i).setToken(new Token(random+3));
        }
        System.out.println("\n");
        for(int i=0; i<playerList.size(); i++){
            playerList.get(i).setLastRoundTurn(i);
        }
        System.out.println("\n");
        player = finalPhase.declareWinner(playerList);
        System.out.println(player.getIDPlayer());
    }

    @Test
    public void TestDeclareWinnerMostPoints(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++) {
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
        }
        playerList.get(0).getScoreMarker().incrementScore(26);
        playerList.get(1).getScoreMarker().incrementScore(67);
        playerList.get(2).getScoreMarker().incrementScore(86);
        playerList.get(3).getScoreMarker().incrementScore(47);
        player=finalPhase.declareWinner(playerList);
        assertEquals(playerList.get(2), player);
    }

    @Test
    public void TestDeclareWinnerMostPrivatePoints(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++) {
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            playerList.get(i).getScoreMarker().incrementScore(26);
        }
        playerList.get(0).setPrivatePoints(44);
        playerList.get(1).setPrivatePoints(36);
        playerList.get(2).setPrivatePoints(18);
        playerList.get(3).setPrivatePoints(28);
        player=finalPhase.declareWinner(playerList);
        assertEquals(playerList.get(0), player);
    }

    @Test
    public void TestDeclareWinnerMostTokens(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++) {
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            playerList.get(i).getScoreMarker().incrementScore(26);
            playerList.get(i).setPrivatePoints(44);
        }
        playerList.get(0).setToken(new Token(0));
        playerList.get(1).setToken(new Token(2));
        playerList.get(2).setToken(new Token(3));
        playerList.get(3).setToken(new Token(1));
        player=finalPhase.declareWinner(playerList);
        assertEquals(playerList.get(2), player);
    }

    @Test
    public void TestDeclareWinnerLastTurn(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++) {
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE));
            playerList.get(i).getScoreMarker().incrementScore(26);
            playerList.get(i).setPrivatePoints(44);
            playerList.get(i).setToken(new Token(0));
        }
        playerList.get(0).setLastRoundTurn(1);
        playerList.get(1).setLastRoundTurn(0);
        playerList.get(2).setLastRoundTurn(2);
        playerList.get(3).setLastRoundTurn(3);
        player=finalPhase.declareWinner(playerList);
        assertEquals(playerList.get(1), player);
    }

}
