package it.polimi.ingsw.LM26.PlayArea;

import java.util.ArrayList;

public class RoundTrack implements RoundTrackInt{

    private ArrayList<RoundTrackTurn> roundTrackTurnList;

    private int currentTurn;

    public RoundTrack(){

        roundTrackTurnList = new ArrayList<RoundTrackTurn>();

        currentTurn = 0;
    }

    public int getCurrentTurn(){

        return currentTurn;
    }
}
