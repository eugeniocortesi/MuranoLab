package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static javafx.scene.input.KeyCode.R;
import static org.junit.Assert.*;

public class TestDrawOneMoreDie8 {


    Model model = singletonModel();
    PlayerZone player;
    Box[][] board;
    Random rand = new Random();

    @Before
    public void setup(){

        player=new PlayerZone("name" , 0);
        model.getPlayerList().add(player);
    }

    @Test
    public void checkEffect() {

        //trying at first turn
        player.getActionHistory().setFirstTurn(true);
        player.getActionHistory().setSecondTurn(false);

        if (!player.getActionHistory().isSecondTurn()) {
            assertTrue(model.getDecks().getToolCardDeck().get(7).play(player.getIDPlayer()));
            assertTrue(model.getRestrictions().isTool8needPlacement());
            System.out.println("ASSERTS OK");
        }

        model.getRestrictions().setNeedPlacement(false);
        //trying at second turn
        player.getActionHistory().setFirstTurn(false);
        player.getActionHistory().setSecondTurn(true);
        assertFalse(model.getDecks().getToolCardDeck().get(7).play(model.getPlayerList().get(0).getIDPlayer()));
        assertFalse(model.getRestrictions().isNeedPlacement());


    }


}