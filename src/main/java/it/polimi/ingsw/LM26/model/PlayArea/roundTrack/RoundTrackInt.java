package it.polimi.ingsw.LM26.model.PlayArea.roundTrack;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class RoundTrackInt implements Serializable{

    String typeRoundTrack;

    public abstract int getCurrentTurn();

    public abstract void addDice( ArrayList<DieInt> ad);

    public abstract ArrayList<DieInt> getRoundTrackTurn(int turn);

    public abstract ArrayList<RoundTrackTurn> getRoundTrackTurnList();

    public abstract void dump();

    public abstract void rewrite();
}
