package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestEventHandler {

    Model model;
    EventChecker checker;
    ArrayList<DieInt> dice=new ArrayList<DieInt>();
    ActionEvent actionEvnet=new ActionEvent();
    Controller controller = new Controller();
    EventHandler eventHandler;
    WindowFramePlayerBoard window;

    @Before
    public void setup(){

        model= singletonModel();
        model.reset();
        for(int i=0; i<5; i++){
            DieInt die= model.getBag().draw();
            die.roll();
            dice.add(die);
        }
        model.getRoundTrackInt().addDice(dice);
        model.getRoundTrackInt().update();
        dice.removeAll(dice);

        for(int i=0; i<7; i++){
            DieInt die= model.getBag().draw();
            die.roll();
            dice.add(die);
        }
        model.getRoundTrackInt().addDice(dice);
        model.getRoundTrackInt().update();
        dice.removeAll(dice);

        for(int i=0; i<3; i++){
            DieInt die= model.getBag().draw();
            die.roll();
            dice.add(die);
        }
        model.getRoundTrackInt().addDice(dice);
        model.getRoundTrackInt().update();
        dice.removeAll(dice);

        model.getRoundTrackInt().dump();
        model.getPlayerList().add(new PlayerZone("name",0));
        window = new WindowFramePlayerBoard(0, Color.ANSI_RED);
        model.getPlayerList().get(0).setPlayerBoard(window);
        actionEvnet.setId(0);
        eventHandler=new EventHandler(actionEvnet, model, controller);
        checker=new EventChecker(model);

}


    @Test

    public void checkHandler(){

        ActionEvent ae= new ActionEvent();
        assertFalse(eventHandler.handle(checker));
        ae.setPlayer(0);
        ae.setId(1);
        assertFalse(eventHandler.handle(checker));
        ae.setId(2);
        assertFalse(eventHandler.handle(checker));
        ae.setCardID(2);
        assertFalse(eventHandler.handle(checker));
        ae.setCardID(3);
        assertFalse(eventHandler.handle(checker));
        ae.setId(3);
        ae.setCardID(4);
        assertFalse(eventHandler.handle(checker));
        ae.setId(4);
        ae.setCardID(9);
        assertFalse(eventHandler.handle(checker));
        ae.setId(5);
        ae.setCardID(5);
        assertFalse(eventHandler.handle(checker));
        ae.setId(6);
        ae.setCardID(1);
        assertFalse(eventHandler.handle(checker));
        ae.setId(7);
        ae.setCardID(6);
        assertFalse(eventHandler.handle(checker));
        ae.setCardID(10);
        assertFalse(eventHandler.handle(checker));
        ae.setCardID(11);
        assertFalse(eventHandler.handle(checker));
        ae.setId(8);
        ae.setCardID(7);
        assertFalse(eventHandler.handle(checker));
        ae.setCardID(8);
        assertFalse(eventHandler.handle(checker));
        ae.setId(9);
        ae.setCardID(11);
        assertFalse(eventHandler.handle(checker));
        ae.setId(10);
        ae.setCardID(12);
        assertFalse(eventHandler.handle(checker));

    }

    @Test
    public void checkEventAttributes() {

        DieInt selected = model.getRoundTrackInt().getRoundTrackTurn(2).get(4);
        selected.dump();
        int[] c = new int[2];
        c[0] = 1;
        c[1] = 4;
        DieInt returned = eventHandler.getTrackDieCopy(c);
        assertEquals(selected, returned);

        actionEvnet.setCardID(model.getOnBoardCards().getToolArrayList().get(1));
        actionEvnet.setPlayer(0);

        assertEquals(eventHandler.getToolCard(), model.getOnBoardCards().getToolCardList().get(1));

        ArrayList<Box> from = new ArrayList<Box>();

        actionEvnet.setPlayer(0);
        actionEvnet.setId(-1);
        actionEvnet.setCardID(-1);
        eventHandler.handle(checker);


        from.add(model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix()[3][3]);
        from.add(model.getPlayerList().get(0).getPlayerBoard().getBoardMatrix()[2][4]);

        ArrayList<Box> returnedList= eventHandler.getBoxListCopy(from);

        assertEquals(returnedList.get(0), from.get(0));

        assertEquals(returnedList.get(1), from.get(1));

        Box returnedBox = eventHandler.getBoxCopy(from.get(0));

        assertEquals(from.get(0), returnedBox);


    }



}