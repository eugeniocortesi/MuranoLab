package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RoundTrackTurn class
 * @author Eugenio Cortesi
 * dice stack of on turn, located on RoundTrack structure
 */

public class RoundTrackTurn implements Serializable {

    private ArrayList<DieInt> diceList;

    public RoundTrackTurn() {
    }

    /**
     * Constructor
     * it creates the stack object and adds dice of the ended round right away
     * @param ad dice list, remained in the draft pool at the end of the a round.
     */

    RoundTrackTurn(ArrayList<DieInt> ad) {

        diceList = new ArrayList<>();

        this.diceList.addAll(ad);
    }

    public ArrayList<DieInt> getDiceList() {

        return diceList;
    }


    /**
     * method is useful to print the cards in tests, System.out can't be avoided
     */

    public void dump() {

        int count = diceList.size();

        System.out.print("elems: ");

        System.out.println(count);

        for (DieInt d : diceList) {

            System.out.print(d);
        }
        System.out.println("\n");
    }

    public void rewrite() {

        if (diceList != null) {

            for (DieInt aDiceList : diceList) {

                aDiceList.rewrite();
            }
        }
    }
}