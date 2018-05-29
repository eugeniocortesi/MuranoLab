package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.util.ArrayList;

public class RoundTrackTurn {

    private ArrayList<DieInt> diceList;

    /*public RoundTrackTurn(){
        diceList = new ArrayList<DieInt>();
    }*/

    public RoundTrackTurn ( ArrayList<DieInt> ad){

        diceList = new ArrayList<DieInt>();
        for (int i = 0; i < ad.size(); i++ ){
            this.diceList.add( ad.get(i));
        }
    }

    public ArrayList<DieInt> getDiceList() {
        return diceList;
    }

    public void dump(){

        int count = diceList.size();
        System.out.print("elems: ");
        System.out.println(count);
        for (DieInt d : diceList){

            System.out.print (d);

        }
        System.out.println("\n");
    }
}
