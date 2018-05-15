package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.Color;
import org.junit.Test;

public class TestDiceSimple {


    Color c = Color.ANSI_YELLOW;
    Die d = new Die(c);
    @Test
    public void checkRoll(){

        d.roll();
        d.dump();
    }

}
