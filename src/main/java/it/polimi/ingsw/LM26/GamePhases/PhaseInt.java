package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public interface PhaseInt {
    public void doAction(Game game, ArrayList<PlayerZone> playerList);
}
