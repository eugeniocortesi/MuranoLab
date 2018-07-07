package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.util.ArrayList;


/**
 * RoundTrack class
 * @author Eugenio Cortesi
 */

public class RoundTrack extends RoundTrackInt {

    private ArrayList<RoundTrackTurn> roundTrackTurnList;

    private int currentTurn;

    public RoundTrack() {
    }

    public RoundTrack(String s) {

        roundTrackTurnList = new ArrayList<>();

        currentTurn = 1;

        this.typeRoundTrack = "RoundTrack";
    }

    public int getCurrentTurn() {

        return currentTurn;
    }

    public void update() {

        this.currentTurn = roundTrackTurnList.size() + 1;
    }


    /**
     * when dice must be added to round track, the methos creates the sub-structure to store them
     * @param ad dice list to be added
     */

    public void addDice(ArrayList<DieInt> ad) {

        RoundTrackTurn r = new RoundTrackTurn(ad);

        roundTrackTurnList.add(r);

    }

    public ArrayList<DieInt> getRoundTrackTurn(int turn) {

        return roundTrackTurnList.get((turn - 1)).getDiceList();
    }

    public ArrayList<RoundTrackTurn> getRoundTrackTurnList() {

        return roundTrackTurnList;
    }

    public void dump() {

        for (RoundTrackTurn aRoundTrackTurnList : roundTrackTurnList) {

            aRoundTrackTurnList.dump();
        }
    }

    @Override
    public void rewrite() {

        this.typeRoundTrack = "RoundTrack";

        if (roundTrackTurnList != null) {

            for (int i = 0; i < roundTrackTurnList.size(); i++) {

                roundTrackTurnList.get(i).rewrite();
            }
        }

    }
}