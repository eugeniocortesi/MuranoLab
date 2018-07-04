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

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestUpdatesHandler {


    Model model = singletonModel();
    Controller controller = new Controller();
    SetupHandler setup ;
    UpdatesHandler updates;

    @Before
    public void setup(){
        model.reset();
        setup = new SetupHandler(controller, model);
        updates = new UpdatesHandler(controller);
    }

    @Test

    public void check(){

      /*  ActionEventPlayer pl1= new ActionEventPlayer("name1", true);
        ActionEventPlayer pl2= new ActionEventPlayer("name1", false);


        updates.updatePlayers(pl1);

        assertEquals(model.getPlayer(0).getName(), "name1");
        assertEquals(model.getPlayer(0).getNumber(), 1);
        updates.updatePlayers(pl2);
        assertEquals(model.getPlayer(0).getPlayerState(), PlayerState.STANDBY);
        updates.updatePlayers(pl1);
        assertEquals(model.getPlayer(0).getPlayerState(), PlayerState.ENDING);

        model.getPlayerList().add(new PlayerZone("name2", 1));
        ActionEventPlayer pl3= new ActionEventPlayer("name1", false);
        ActionEventPlayer pl4= new ActionEventPlayer("name2", false);
        updates.updatePlayers(pl3);
        updates.updatePlayers(pl4);
        CentralPhase central =new CentralPhase();
        controller.setGamePhase(central);
        assertTrue(central.getOnePlayer());

        model.getPlayerList().get(0).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
        controller.playersReady();
        assertEquals( null , controller.getRoundsHandler());
        model.getPlayerList().get(1).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(1));
        controller.playersReady();
        assertNotNull(controller.getRoundsHandler());

        ActionEventTimerEnd actionEventTimerEnd = new ActionEventTimerEnd("s", true);
        actionEventTimerEnd.setName("name1");

        updates.updateActionEventTimerEnd(actionEventTimerEnd);
        assertEquals(model.getPlayer(0).getName(), "name2");
*/
    }
}