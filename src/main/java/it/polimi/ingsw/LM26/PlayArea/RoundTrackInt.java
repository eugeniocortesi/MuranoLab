package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public interface RoundTrackInt{

    public int getCurrentTurn();

    public void addDice( ArrayList<DieInt> ad);
}
