package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.BEGINNING;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.ENDING;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRound {

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
    int standby=0, ending=0, begin=0;
    private Game game;

    @Before
    public void setup(){

        Logger.getLogger(Controller.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

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
        playerZones = playerList;

        game=new Game();  //initialPhase
        game.getPhase().doAction(game);
        central=game.getPhase();
    }

    @Test

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
        assertEquals(1, model.getRoundTrackInt().getRoundTrackTurnList().size());
    }

    @Test

    public void checkEndActionNextPlayerStandby() {
        round = new Round(central.getTurn());
        model.getPlayerList().get(3).setPlayerState(PlayerState.STANDBY);
        bag = new Bag();
        round = new Round(central.getTurn());
        player = model.getPlayerList().get(0);
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
        assertEquals(1,  model.getRoundTrackInt().getRoundTrackTurnList().size());
    }

    @Test
    public void checkNextPlayer() {

        int j=0;
        PlayerZone playing=null;
        playerZones = model.getPlayerList();
        model.setBag(new Bag());
        Game game=new Game();  //initialPhase
        game.getPhase().doAction(game);
        PlayerZone previous;

        while (!game.getPhase().getOnePlayer() && j < game.getPhase().getNrounds()  ) {

            playing = game.getPhase().getCurrentRound().nextPlayer();
            System.out.println("     NUOVO ROUND");

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {


                    System.out.println("              CHANGE TURN: " + playing.getName());

                    int n = game.getPhase().getCurrentRound().getTurnCounter();

                    if (n < (game.getPhase().getTurn().length / 2) - 1) {
                        assertTrue(playing.getActionHistory().isFirstTurn());
                        assertFalse(playing.getActionHistory().isSecondTurn());
                    }

                    if (n > (game.getPhase().getTurn().length / 2) - 1) {
                        assertTrue(playing.getActionHistory().isSecondTurn());
                        assertFalse(playing.getActionHistory().isFirstTurn());
                    }

                    playing.getActionHistory().setPlacement(true);
                    playing.getActionHistory().setJump(true);
                    playing.getActionHistory().setCardUsed(true);
                    playing.getActionHistory().setDieUsed(true);
                    playing.getActionHistory().setFreezed(true);

                    model.getRestrictions().setNeedPlacement(true);
                    model.getRestrictions().setFirstPart(true);
                    model.getRestrictions().setTool8needPlacement(true);
                    model.getRestrictions().setCurrentPlacement(true);
                    model.getRestrictions().setColor("verde");
                    model.getRestrictions().setDie(new Die());

                    game.getPhase().getCurrentRound().endAction();

                    if (n < (game.getPhase().getTurn().length) - 1 && game.getPhase().getTurn()[n] == game.getPhase().getTurn()[n + 1]) {
                        assertTrue(playing.getActionHistory().isSecondTurn());
                        assertFalse(playing.getActionHistory().isFirstTurn());
                    }

                    //check reset partial action history after a turn && restrictions

                    assertFalse(playing.getActionHistory().isPlacement() && playing.getActionHistory().isDieUsed()
                            && playing.getActionHistory().isJump() && playing.getActionHistory().isCardUsed());

                    if (n < (game.getPhase().getTurn().length) - 1) assertTrue(playing.getActionHistory().isFreezed());
                    else {
                        assertFalse(playing.getActionHistory().isFreezed());
                        assertFalse(playing.getActionHistory().isSecondTurn());
                    }

                    assertFalse(model.getRestrictions().isNeedPlacement() && model.getRestrictions().isFirstPart()
                            && model.getRestrictions().isTool8needPlacement()
                            && model.getRestrictions().isCurrentPlacement());

                    assertEquals(model.getRestrictions().getDie(), null);
                    assertEquals(model.getRestrictions().getColor(), null);

                    playing = game.getPhase().getCurrentRound().nextPlayer();


            }

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);

            //check reset all action history after a round like rounds reset and freezing

            assertFalse(playing.getActionHistory().isSecondTurn() && playing.getActionHistory().isFreezed() );

            if(j!=9)assertTrue(playing.getActionHistory().isFirstTurn());

            j++;
        }
    }



    @Test
    public void checkStandby() {
        System.out.println("      ____________________________________       ");
        int j=0, i=0;
        PlayerZone playing;
        playerZones = model.getPlayerList();
        model.setBag(new Bag());
        Game game=new Game();  //initialPhase
        game.getPhase().doAction(game);
        int myStandBycounter=0;

        while (j < game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer();

            System.out.println("     NUOVO ROUND");

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {


                    System.out.println("              CHANGE TURN: " + playing.getName());

                    if (i == 5) {
                        playerZones.get(1).setPlayerState(PlayerState.STANDBY);
                        count();
                        System.out.println(playerZones.get(1).getName() + " went in STANDBY");
                        myStandBycounter++;
                        assertEquals(standby,myStandBycounter);
                        assertEquals(ending, 3-myStandBycounter);
                        assertEquals(begin, 1);
                    }

                    if (i == 9) {
                        playerZones.get(3).setPlayerState(PlayerState.STANDBY);
                        count();
                        System.out.println(playerZones.get(3).getName() + " went in STANDBY");
                        myStandBycounter++;
                        assertEquals(standby,myStandBycounter);
                        assertEquals(ending, 3-myStandBycounter);
                        assertEquals(begin, 1);
                    }
                    if (i == 12) {
                        playerZones.get(0).setPlayerState(PlayerState.STANDBY);
                        count();
                        System.out.println(playerZones.get(0).getName() + " went in STANDBY");
                        myStandBycounter++;
                        assertEquals(standby,myStandBycounter);
                        assertEquals(ending, 3-myStandBycounter);
                        assertEquals(begin, 1);
                    }
                    if (i == 16) {
                        if (playerZones.get(1).getPlayerState()!=BEGINNING) {
                            playerZones.get(1).setPlayerState(PlayerState.ENDING);
                            count();
                            System.out.println(playerZones.get(1).getName() + " exit STANDBY");
                            myStandBycounter--;
                            assertEquals(standby,myStandBycounter);
                            assertEquals(ending, 3-myStandBycounter);
                            assertEquals(begin, 1);
                        }
                    }
                    if (i == 19) {
                        if (playerZones.get(3).getPlayerState()!=BEGINNING) {
                            playerZones.get(3).setPlayerState(PlayerState.ENDING);
                            count();
                            System.out.println(playerZones.get(3).getName() + "exit STANDBY");
                            myStandBycounter--;
                            assertEquals(standby,myStandBycounter);
                            assertEquals(ending, 3-myStandBycounter);
                            assertEquals(begin, 1);
                        }
                    }
                    if (i == 22) {
                        playerZones.get(2).setPlayerState(PlayerState.STANDBY);
                        count();
                        System.out.println(playerZones.get(2).getName() + " went in STANDBY");
                        myStandBycounter++;
                        assertEquals(standby,myStandBycounter);
                        assertEquals(ending, 3-myStandBycounter);
                        assertEquals(begin, 1);
                    }

                    if (i == 23) {
                        playerZones.get(3).setPlayerState(PlayerState.STANDBY);
                        count();
                        System.out.println(playerZones.get(3).getName() + "went STANDBY");
                        myStandBycounter++;
                        assertEquals(standby,myStandBycounter);
                        assertEquals(ending, 3-myStandBycounter);
                        assertEquals(begin, 1);
                    }

                    if (i == 26) {
                        if (playerZones.get(1).getPlayerState()!=BEGINNING) {
                            playerZones.get(1).setPlayerState(PlayerState.ENDING);
                            count();
                            System.out.println(playerZones.get(1).getName() + " exit STANDBY");
                            myStandBycounter--;
                            assertEquals(standby,myStandBycounter);
                            assertEquals(ending, 3-myStandBycounter);
                            assertEquals(begin, 1);
                        }
                    }

                    i++;

                    game.getPhase().getCurrentRound().endAction();

                    if(game.getPhase().getCurrentRound().getRoundState()!=FINISHED)

                     playing = game.getPhase().getCurrentRound().nextPlayer();

            }

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);

            j++;
        }

    }

    public void count(){

        standby=0; ending=0; begin=0;
        for(int i=0;i<model.getPlayerList().size();i++){
            if (model.getPlayer(i).getPlayerState()==STANDBY)standby++;
            if (model.getPlayer(i).getPlayerState()==ENDING)ending++;
            if (model.getPlayer(i).getPlayerState()==BEGINNING)begin++;
        }

    }
}


