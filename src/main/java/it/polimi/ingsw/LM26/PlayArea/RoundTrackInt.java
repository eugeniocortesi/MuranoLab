package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public interface RoundTrackInt{

    public int getCurrentTurn();

    public PlayerZone getLast();

    public void setLast(PlayerZone last);
}
