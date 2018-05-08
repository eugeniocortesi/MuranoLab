package it.polimi.ingsw.LM26.PlayArea;

import java.util.ArrayList;

public class RoundTrack implements RoundTrackInt{

    private ArrayList<RoundTrackTurn> roundTrackTurnList;

    private int currentTurn;

    public RoundTrack(){

        roundTrackTurnList = new ArrayList<RoundTrackTurn>();

        currentTurn = 1;
    }

    public int getCurrentTurn(){

        return currentTurn;
    }

    public void update(){

        this.currentTurn = roundTrackTurnList.size()+1;
    }

    public void addDice( ArrayList<DieInt> ad){

        RoundTrackTurn r = new RoundTrackTurn(ad);

        roundTrackTurnList.add(r);

    }

    public void dump(){

        for (int i = 0; i< roundTrackTurnList.size(); i++){

            this.roundTrackTurnList.get(i).dump();
        }
    }
}
