package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.S;
import static org.junit.Assert.*;

public class TestDrawOneMoreDie8 {


    Model model;
    PlayerZone player;
    Box[][] board;

    @Before
    public void setup(){
        model= singletonModel();
        model.reset();



        player=new PlayerZone("name" , 0);
        model.getPlayerList().add(player);
        System.out.print(model.getPlayerList().get(0).getName());
    }

    @Test
    public void checkEffect() {

        //trying at first turn
        player.getActionHistory().setFirstTurn(true);
        player.getActionHistory().setSecondTurn(false);

        if (!player.getActionHistory().isSecondTurn()) {
            assertTrue(model.getDecks().getToolCardDeck().get(7).play(player));
            assertTrue(model.getRestrictions().isTool8needPlacement());
            System.out.println("ASSERTS OK");
        }

        model.getRestrictions().setNeedPlacement(false);
        //trying at second turn
        player.getActionHistory().setFirstTurn(false);
        player.getActionHistory().setSecondTurn(true);
        assertFalse(model.getDecks().getToolCardDeck().get(7).play(model.getPlayerList().get(0)));
        assertFalse(model.getRestrictions().isNeedPlacement());


    }


}