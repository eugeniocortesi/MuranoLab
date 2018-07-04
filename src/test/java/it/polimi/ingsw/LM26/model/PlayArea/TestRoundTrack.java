package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import org.junit.Test;

import java.util.ArrayList;

public class TestRoundTrack {

    RoundTrack roundTrack = new RoundTrack("s");
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
