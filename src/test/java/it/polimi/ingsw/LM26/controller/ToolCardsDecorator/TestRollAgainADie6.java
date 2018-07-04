package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestRollAgainADie6 {

    Model model = singletonModel();


    @Test
    public void checkEffect() {

        model.getPlayerList().add(new PlayerZone("name", 0));
        DieInt die = new Die(Color.ANSI_RED);
        die.roll();

        model.getDecks().getToolCardDeck().get(5).play(die, model.getPlayerList().get(0));
        if (!model.getPlayerList().get(0).getActionHistory().isDieUsed() && !model.getPlayerList().get(0).getActionHistory().isPlacement()) {
            assertEquals(model.getRestrictions().getDie(), die);
            assertTrue(model.getRestrictions().isNeedPlacement());
        }
    }
}