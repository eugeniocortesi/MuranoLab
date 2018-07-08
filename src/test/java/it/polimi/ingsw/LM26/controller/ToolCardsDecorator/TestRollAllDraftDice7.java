package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestRollAllDraftDice7 {

    Model model = singletonModel();

    @Before
    public void setup(){
        model.reset();

        model.getPlayerList().add(new PlayerZone("name", 0));

        for(int i=0; i<5;i++){
            DieInt die =model.getBag().draw();
            die.roll();
            model.getDraftPool().addDie(die);
        }
        model.getDraftPool().printDraftPool();
    }

    @Test
    public void checkEffect() {

        //trying at first turn
        model.getPlayerList().get(0).getActionHistory().setFirstTurn(true);
        model.getPlayerList().get(0).getActionHistory().setSecondTurn(false);

        //it must enter the else
        if (model.getPlayerList().get(0).getActionHistory().isSecondTurn() && !model.getPlayerList().get(0).getActionHistory().isDieUsed()){
            assertTrue(model.getDecks().getToolCardDeck().get(6).play(model.getPlayerList().get(0)));
            model.getDraftPool().printDraftPool();
        }
        else assertFalse(model.getDecks().getToolCardDeck().get(6).play(model.getPlayerList().get(0)));

        //trying at second turn
        model.getPlayerList().get(0).getActionHistory().setFirstTurn(false);
        model.getPlayerList().get(0).getActionHistory().setSecondTurn(true);

        //it must enter the then
        if (model.getPlayerList().get(0).getActionHistory().isSecondTurn() && !model.getPlayerList().get(0).getActionHistory().isDieUsed()){
            assertTrue(model.getDecks().getToolCardDeck().get(6).play(model.getPlayerList().get(0)));
            model.getDraftPool().printDraftPool();
        }
        else assertFalse(model.getDecks().getToolCardDeck().get(6).play(model.getPlayerList().get(0)));

    }

}