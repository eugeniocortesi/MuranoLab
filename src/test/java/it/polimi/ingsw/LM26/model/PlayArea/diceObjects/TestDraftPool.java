package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDraftPool {

    DraftPool draftPool = new DraftPool();

    @Test
    public void checkDraftPool(){

        Bag bag = new Bag();

        for ( int i=0; i< 8; i++){

            Die d = (Die) bag.draw();
            d.roll();
            draftPool.addDie(d);
        }

        draftPool.printDraftPool();

        draftPool.removeAllDice();

        System.out.println("Removed all dice");

        draftPool.printDraftPool();

    }

}