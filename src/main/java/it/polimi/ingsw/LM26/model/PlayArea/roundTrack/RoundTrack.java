package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.util.ArrayList;

public class RoundTrack extends RoundTrackInt{

    private ArrayList<RoundTrackTurn> roundTrackTurnList;

    private int currentTurn;

    public RoundTrack(){
    }

    public RoundTrack(String s){

        roundTrackTurnList = new ArrayList<RoundTrackTurn>();

        currentTurn = 1;
        this.typeRoundTrack="RoundTrack";
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

    public ArrayList<DieInt> getRoundTrackTurn(int turn){
        return roundTrackTurnList.get((turn-1)).getDiceList();
    }

    public void dump(){

        for (int i = 0; i< roundTrackTurnList.size(); i++){

            this.roundTrackTurnList.get(i).dump();
        }
    }

    @Override
    public void rewrite() {

        this.typeRoundTrack="RoundTrack";

        if(roundTrackTurnList!=null){

            for(int i=0; i< roundTrackTurnList.size(); i++){

                roundTrackTurnList.get(i).rewrite();
            }
        }

    }

    public ArrayList<RoundTrackTurn> getRoundTrackTurnList() {
        return roundTrackTurnList;
    }
}
