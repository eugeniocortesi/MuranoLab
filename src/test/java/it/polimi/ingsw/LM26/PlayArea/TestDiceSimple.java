package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.Color;
import it.polimi.ingsw.LM26.PlayArea.Die;
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
