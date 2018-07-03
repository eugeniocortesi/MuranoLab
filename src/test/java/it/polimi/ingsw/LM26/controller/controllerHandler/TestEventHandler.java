package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestEventHandler {

    Model model= new Model("s");
    ArrayList<DieInt> dice=new ArrayList<DieInt>();
    ActionEvent actionEvnet=new ActionEvent();
    Controller controller = new Controller();
    EventHandler eventHandler = new EventHandler(actionEvnet, model, controller);

    @Before
    public void setup(){

        model.setDecks(new Decks());
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

        for(int i=0; i<model.getOnBoardCards().getToolCardList().size(); i++)
            model.getOnBoardCards().getToolCardList().get(i).getNum();

        for(int i=0; i<model.getOnBoardCards().getToolArrayList().size(); i++)
            model.getOnBoardCards().getToolArrayList().get(i);

        System.out.print(model.getOnBoardCards().getToolArrayList().get(1));

        System.out.print( model.getOnBoardCards().getToolCardList().get(1).getNum());

        assertEquals(eventHandler.getToolCard(), model.getOnBoardCards().getToolCardList().get(1));

        WindowFramePlayerBoard window = new WindowFramePlayerBoard(0, Color.ANSI_RED);

        ArrayList<Integer> from = new ArrayList<Integer>();
        ArrayList<Integer> to = new ArrayList<Integer>();




    }



}