package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import org.junit.Test;

public class TestDie {

    Bag bag = new Bag();



    @Test

    public void checkDieBehavior(){

    DieInt die = bag.draw();
    die.roll();
    System.out.println(die.getValue());
    die.dump();
    die.increment();
    System.out.println(die.getValue());
    die.dump();
    die.decrement();
    System.out.println(die.getValue());
    die.dump();
    }


}