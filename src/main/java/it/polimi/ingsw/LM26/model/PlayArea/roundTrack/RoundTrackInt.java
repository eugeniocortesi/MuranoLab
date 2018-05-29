package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.util.ArrayList;

public interface RoundTrackInt{

    public int getCurrentTurn();

    public void addDice( ArrayList<DieInt> ad);

    public ArrayList<DieInt> getRoundTrackTurn(int turn);

    public ArrayList<RoundTrackTurn> getRoundTrackTurnList();

    public void dump();
}
