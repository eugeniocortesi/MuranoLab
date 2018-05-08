package it.polimi.ingsw.LM26.PlayArea;

import org.junit.Test;

import java.util.ArrayList;

public class TestRoundTrack {

    RoundTrack roundTrack = new RoundTrack();
    Bag bag = new Bag();

    @Test
    public void checkRoundTrack(){

        ArrayList<DieInt> dieInts = new ArrayList<DieInt>();

        for( int i = 0; i <10; i++ ) {

            for (int j = 0; j < 5; j++) {

                DieInt d = bag.draw();
                d.roll();
                dieInts.add( d);
                System.out.println(" Questo e' il " +j+ " dado");
                System.out.println(d);

            }

            System.out.println(roundTrack.getCurrentTurn());
            roundTrack.addDice(dieInts);
            roundTrack.update();
            roundTrack.dump();
        }

    }
}
