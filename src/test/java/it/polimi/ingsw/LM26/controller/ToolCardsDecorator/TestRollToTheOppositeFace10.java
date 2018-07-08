package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;

public class TestRollToTheOppositeFace10 {

    Model model = singletonModel();

    PlayerZone player;

    @Before
    public void setup(){

        model.reset();
        player=new PlayerZone("name" , 0);

        model.getPlayerList().add(player);
    }

    @Test
    public void checkEffect(){

        DieInt die = new Die(Color.ANSI_RED);

        die.setRoll(1);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 6);

        die = new Die(Color.ANSI_RED);

        die.setRoll(2);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 5);

        die = new Die(Color.ANSI_RED);

        die.setRoll(3);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 4);

        die = new Die(Color.ANSI_RED);

        die.setRoll(4);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 3);

        die = new Die(Color.ANSI_RED);

        die.setRoll(5);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 2);

        die = new Die(Color.ANSI_RED);

        die.setRoll(6);

        model.getDecks().getToolCardDeck().get(9).play(die, player);

        assertEquals(die.getValue(), 1);


    }
}