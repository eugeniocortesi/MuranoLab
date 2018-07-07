package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.controller.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.ENDING;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestUpdatesHandler {


    Model model = singletonModel();
    Controller controller = new Controller();


    @Before
    public void setup(){
        model.reset();
        controller.startServer();


    }

    @Test
    public void check(){
        /*

        ActionEventPlayer pl1= new ActionEventPlayer("name1", true);
        ActionEventPlayer pl2= new ActionEventPlayer("name1", false);


        controller.getUpdatesHandler().updatePlayers(pl1);

        assertEquals(model.getPlayer(0).getName(), "name1");
        assertEquals(model.getPlayer(0).getNumber(), 1);
        controller.getUpdatesHandler().updatePlayers(pl2);
        assertEquals(model.getPlayer(0).getPlayerState(), PlayerState.STANDBY);
        controller.getUpdatesHandler().updatePlayers(pl1);
        assertEquals(model.getPlayer(0).getPlayerState(), PlayerState.ENDING);

        model.getPlayerList().add(new PlayerZone("name2", 1));


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


        model.getDecks().getObjectivePrivateCards().get(0).setPlayer(player1);
        model.getDecks().getObjectivePrivateCards().get(1).setPlayer(player2);
        model.getDecks().getObjectivePrivateCards().get(2).setPlayer(player3);


        model.setPlayerList(playerList);

        ActionEventPlayer pl3= new ActionEventPlayer("eugenio", false);
        ActionEventPlayer pl4= new ActionEventPlayer("Chiara", false);

        CentralPhase central =new CentralPhase();
        controller.setGamePhase(central);
        controller.getUpdatesHandler().updatePlayers(pl3);
        controller.getUpdatesHandler().updatePlayers(pl4);
        assertTrue(central.getOnePlayer());

        model.getPlayerList().get(0).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
        controller.playersReady();
        assertEquals( null , controller.getRoundsHandler());
        model.getPlayerList().get(1).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(1));
        controller.playersReady();
        assertNotNull(controller.getRoundsHandler());

        ActionEventTimerEnd actionEventTimerEnd = new ActionEventTimerEnd("s", true);
        actionEventTimerEnd.setName("name1");

        controller.getUpdatesHandler().updateActionEventTimerEnd(actionEventTimerEnd);
        assertEquals(model.getPlayer(0).getName(), "name2");
*/
    }
}