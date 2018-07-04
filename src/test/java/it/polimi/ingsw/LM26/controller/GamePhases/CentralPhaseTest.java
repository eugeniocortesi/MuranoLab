package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.ENDING;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertArrayEquals;

public class CentralPhaseTest {

    private ArrayList<PlayerZone> playerZones= new ArrayList<PlayerZone>();
    private String name="name";
    private int[] v1 ={1,2,3,3,2,1};
    private int[] v2 ={1,2,3,4,4,3,2,1};
    private int[] v3 ={2,3,1,1,3,2};
    private int[] v4 ={3,1,2,2,1,3};
    private int[] v5 ={2,3,4,1,1,4,3,2};

    private RoundTrack roundTrack= new RoundTrack("s");
    private Model model;


    @Before
    public void setup(){

        model= singletonModel();

        PlayerZone player1 = new PlayerZone("eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        PlayerZone player3 = new PlayerZone( "Claudia", 2);


        player1.setPlayerState(ENDING);
        player2.setPlayerState(ENDING);
        player3.setPlayerState(ENDING);

        player1.setNumberPlayer(1);
        player1.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
        player2.setNumberPlayer(2);
        player2.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(1));
        player3.setNumberPlayer(3);
        player3.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(2));


        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);


        model.getDecks().getObjectivePrivateCardDeck().get(0).setPlayer(player1);
        model.getDecks().getObjectivePrivateCardDeck().get(1).setPlayer(player2);
        model.getDecks().getObjectivePrivateCardDeck().get(2).setPlayer(player3);


        model.setPlayerList(playerList);

    }

    @Test
    public void checkSetOrder234players(){

        CentralPhase centralPhase = new CentralPhase();
        assertArrayEquals(centralPhase.getTurn(), v1);

        centralPhase.resetOrder(model.getPlayerList().size());
        assertArrayEquals(centralPhase.getTurn(), v3);

        centralPhase.resetOrder(model.getPlayerList().size());
        assertArrayEquals(centralPhase.getTurn(), v4);

        centralPhase.resetOrder(model.getPlayerList().size());
        assertArrayEquals(centralPhase.getTurn(), v1);

        PlayerZone player4 = new PlayerZone("Tommaso", 3);
        player4.setPlayerState(ENDING);
        player4.setNumberPlayer(4);
        player4.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(3));
        model.getDecks().getObjectivePrivateCardDeck().get(3).setPlayer(player4);
        model.getPlayerList().add(player4);

        centralPhase = new CentralPhase();
        assertArrayEquals(centralPhase.getTurn(), v2);
        centralPhase.resetOrder(model.getPlayerList().size());
        assertArrayEquals(centralPhase.getTurn(), v5);



    }
}