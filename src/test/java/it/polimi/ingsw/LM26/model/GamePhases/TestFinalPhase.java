package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.controller.GamePhases.FinalPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.controller.GamePhases.InitialPhase;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;

public class TestFinalPhase {

    private ArrayList<PlayerZone> playerList= new ArrayList<PlayerZone>();
    private Random rand = new Random();
    private final int roundTrackSize = 100;
    private int random;
    private PlayerZone player;
    Model model;

    @Before
    public void FinalPhaseSetUp(){
        model= singletonModel();
        model.reset();
        for(int i=0; i<4; i++){
            playerList.add(new PlayerZone("name", i+1));
        }

    }

    @Test
    public void testDeclareWinnerRandom(){
        FinalPhase finalPhase= new FinalPhase();
        for(int i=0; i<playerList.size(); i++){
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE,playerList.get(i)));
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
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE, playerList.get(i)));
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
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE, playerList.get(i)));
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
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE, playerList.get(i)));
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
            playerList.get(i).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE, playerList.get(i)));
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

    @Test
    public void checkScore() {

        model = new Model("s");
        model.setDecks(new Decks());
        for (int i = 0; i < 2; i++) {
            model.getPlayerList().add(new PlayerZone("name" + i, i));
            model.getPlayerList().get(i).setNumberPlayer(i);

            Random rand = new Random();
            int index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            WindowPatternCard pattern = model.getDecks().getWindowPatternCardDeck().get(index);
            model.getPlayerList().get(i).setWindowPatternCard(pattern);
            model.getDecks().getObjectivePrivateCardDeck().get(i).setPlayer(model.getPlayerList().get(i));

        }

        Game game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());

        for (int i = 0; i < 2; i++) {
            Box[][] b = model.getPlayerList().get(i).getPlayerBoard().getBoardMatrix();

            DieInt die;
            die = new Die(Color.ANSI_BLUE);
            die.roll();
            b[0][0].setDie(die);
            die = new Die(Color.ANSI_RED);
            die.roll();
            b[1][0].setDie(die);
            die = new Die(Color.ANSI_RED);
            die.roll();
            b[1][3].setDie(die);
            die = new Die(Color.ANSI_PURPLE);
            die.roll();
            b[1][2].setDie(die);
            die = new Die(Color.ANSI_GREEN);
            die.roll();
            b[1][3].setDie(die);
            die = new Die(Color.ANSI_BLUE);
            die.roll();
            b[1][1].setDie(die);
            die = new Die(Color.ANSI_BLUE);
            die.roll();
            b[2][0].setDie(die);
            die = new Die(Color.ANSI_BLUE);
            die.roll();
            b[2][2].setDie(die);
            die = new Die(Color.ANSI_BLUE);
            die.roll();
            b[3][3].setDie(die);
            die = new Die(Color.ANSI_YELLOW);
            die.roll();
            b[1][4].setDie(die);
            die = new Die(Color.ANSI_YELLOW);
            die.roll();
            b[2][3].setDie(die);
            die = new Die(Color.ANSI_YELLOW);
            die.roll();
            b[3][2].setDie(die);

            model.getPlayerList().get(i).getPlayerBoard().printCard();
            model.getPlayerList().get(i).getWindowPatternCard().printCard();

        }

        FinalPhase fin = new FinalPhase();
        fin.computeScore();
        for (int i = 0; i < model.getPlayerList().size(); i++){
            System.out.println("points player " + i + " " + model.getPlayerList().get(i).getScoreMarker().getRealPoints());
            System.out.println("on scorTrack: " + model.getPlayerList().get(i).getScoreMarker().getPoints());
        }
        System.out.println("winner is: " + fin.declareWinner(model.getPlayerList()).getName());

    }

}
