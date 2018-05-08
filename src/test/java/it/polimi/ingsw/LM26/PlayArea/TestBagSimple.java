package it.polimi.ingsw.LM26.PlayArea;
import it.polimi.ingsw.LM26.Cards.Color;
import it.polimi.ingsw.LM26.PlayArea.Bag;
import org.junit.Test;

public class TestBagSimple {

    Bag bag = new Bag();

    @Test
    public void checkBag(){

        bag.dump();
        System.out.println("--------");
        for (int i = 0; i <  90; i++) {
            DieInt d = bag.draw();
            d.roll();
            System.out.println("d: " + d);
            //d.dump();
            bag.dump();
        }
    }

}
