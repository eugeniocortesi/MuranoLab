package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.ENDING;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;

public class TestRound {

    private RoundTrack roundTrack = new RoundTrack("s");
    private DraftPool draftPool = new DraftPool("s");
    private String name = "name";
    private Round round;
    private int nrounds = 10, contEnd, contBeg, contSta;
    private int turn[] = {1, 2, 3, 4, 4, 3, 2, 1};
    private Bag bag;
    private ArrayList<DieInt> dice = new ArrayList<DieInt>();
    private PlayerZone player;
    private ArrayList<PlayerZone> playerZones = new ArrayList<PlayerZone>();
    private Model model;
    private PhaseInt central;
    private Game game;

    @Before
    public void setup(){

        model= singletonModel();
        model.reset();

        PlayerZone player1 = new PlayerZone("eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        PlayerZone player3 = new PlayerZone( "Claudia", 2);
        PlayerZone player4 = new PlayerZone("Tommaso", 3);

        player1.setPlayerState(ENDING);
        player2.setPlayerState(ENDING);
        player3.setPlayerState(ENDING);
        player4.setPlayerState(ENDING);

        player1.setNumberPlayer(1);
        player1.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
        player2.setNumberPlayer(2);
        player2.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(1));
        player3.setNumberPlayer(3);
        player3.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(2));
        player4.setNumberPlayer(4);
        player4.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(3));

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        model.getDecks().getObjectivePrivateCardDeck().get(0).setPlayer(player1);
        model.getDecks().getObjectivePrivateCardDeck().get(1).setPlayer(player2);
        model.getDecks().getObjectivePrivateCardDeck().get(2).setPlayer(player3);
        model.getDecks().getObjectivePrivateCardDeck().get(3).setPlayer(player4);

        model.setPlayerList(playerList);

        game=new Game();  //initialPhase
        game.getPhase().doAction(game);
        central=game.getPhase();
    }

    /*@Test
    //4 giocatori, controlla che solo a fine round lo stato sia "FINISHED",
    // che il dado nella draftpool sia trasferito nella roundtrack,
    // controlla che ad ogni turno (senza disconnessioni) 3 giocatori siano in "ENDING" e uno sia in "BEGINNING"
    public void checkEndActionNextPlayer() {


        round = new Round(central.getTurn());
        bag = new Bag();
        round.nextPlayer();
        for (int i = 0; i < (turn.length - 1); i++) {
            round.endAction();
            round.nextPlayer();
            contBeg = 0;
            contEnd = 0;
            for (PlayerZone j : playerZones) {
                if (j.getPlayerState().equals(PlayerState.ENDING)) contEnd++;
                else contBeg++;
            }
            assertEquals(3, contEnd);
            assertEquals(1, contBeg);
            assertEquals(RoundState.RUNNING, round.getRoundState());
        }
        dice.add(bag.draw());
        draftPool.setInDraft(dice);
        round.endAction();
        assertEquals(RoundState.FINISHED, round.getRoundState());
        assertEquals(1, roundTrack.getRoundTrackTurn(1).size());
    }

    @Test
    //test uguale a quello di prima, ma prova il caso di player 4 in standby
    public void checkEndActionNextPlayerStandby() {
        round = new Round(central.getTurn());
        model.getPlayerList().get(3).setPlayerState(PlayerState.STANDBY);
        bag = new Bag();
        round = new Round(central.getTurn());
        player = model.getPlayerList().get(0); //per non far finire il turno al giocatore in standby
        player = round.nextPlayer();
        while (round.getTurnCounter() < (turn.length - 1)) {
            round.endAction();
            player = round.nextPlayer();
            System.out.println(player.getNumber());
            contBeg = 0;
            contEnd = 0;
            contSta = 0;
            for (PlayerZone j : model.getPlayerList()) {
                if (j.getPlayerState().equals(PlayerState.ENDING)) contEnd++;
                else if (j.getPlayerState().equals(PlayerState.BEGINNING)) contBeg++;
                else contSta++;
            }
            assertEquals(1, contBeg);
            assertEquals(1, contSta);
            assertEquals(2, contEnd);
            assertEquals(RoundState.RUNNING, round.getRoundState());
        }
        dice.add(bag.draw());
        draftPool.setInDraft(dice);
        round.endAction();
        assertEquals(RoundState.FINISHED, round.getRoundState());
        assertEquals(1, roundTrack.getRoundTrackTurn(1).size());
    }*/

    @Test
    public void checkNextPlayer() {

        int j=0;
        PlayerZone playing;
        playerZones = model.getPlayerList();
        model.setBag(new Bag());
        Game game=new Game();  //initialPhase
        game.getPhase().doAction(game);

        while (j < game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer();

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {

                System.out.println("              CHANGE TURN: " + playing.getName());

                game.getPhase().getCurrentRound().endAction();

                playing = game.getPhase().getCurrentRound().nextPlayer();

            }

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);
        }
    }



    @Test
    public void checkStandby() {

        int j=0, i=0;
        PlayerZone playing;
        playerZones = model.getPlayerList();
        model.setBag(new Bag());
        Game game=new Game();  //initialPhase
        game.getPhase().doAction(game);

        while (j < game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer();

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {

                System.out.println("              CHANGE TURN: " + playing.getName());

                if (i == 5) {
                    playerZones.get(1).setPlayerState(PlayerState.STANDBY);
                    System.out.println(playerZones.get(1).getName() + " went in STANDBY");
                }
                if (i == 9) {
                    playerZones.get(3).setPlayerState(PlayerState.STANDBY);
                    System.out.println(playerZones.get(3).getName() + " went in STANDBY");
                }
                if (i == 12) {
                    playerZones.get(0).setPlayerState(PlayerState.STANDBY);
                    System.out.println(playerZones.get(0).getName() + " went in STANDBY");
                }
                if (i == 17) {
                    playerZones.get(1).setPlayerState(PlayerState.ENDING);
                    System.out.println(playerZones.get(1).getName() + " exit STANDBY");
                }
                if (i == 20) {
                    playerZones.get(3).setPlayerState(PlayerState.ENDING);
                    System.out.println(playerZones.get(3).getName() + "exit STANDBY");
                }
                if (i == 24) {
                    playerZones.get(2).setPlayerState(PlayerState.STANDBY);
                    System.out.println(playerZones.get(2).getName() + " went in STANDBY");
                }

                i++;

                game.getPhase().getCurrentRound().endAction();

                playing = game.getPhase().getCurrentRound().nextPlayer();

            }

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);
        }

    }
}


