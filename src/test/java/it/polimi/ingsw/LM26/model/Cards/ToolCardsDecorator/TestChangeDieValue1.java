package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestChangeDieValue1 {

    Model model = singletonModel();
    DieInt die= null;
    int value=0;

    @Test
    public void checkEffect(){

        die=model.getBag().draw();

        die.roll();

        while (die.getValue()== 1)

            die.roll();

        value=die.getValue();

        die.dump();

        model.getDecks().getToolCardDeck().get(0).play(die, "decrement");

        assertEquals(value-1, die.getValue());

        die.roll();

         while (die.getValue()== 6)

                 die.roll();

        value=die.getValue();

        die.dump();

        model.getDecks().getToolCardDeck().get(0).play(die, "increment");

        assertEquals(value+1, die.getValue());

        die.setRoll(1);

        assertFalse(model.getDecks().getToolCardDeck().get(0).play(die, "decrement"));

        die.setRoll(6);

        assertFalse(model.getDecks().getToolCardDeck().get(0).play(die, "increment"));
    }
}