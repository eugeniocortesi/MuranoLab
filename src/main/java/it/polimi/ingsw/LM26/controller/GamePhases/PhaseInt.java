package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public interface PhaseInt {

    public void doAction(Game game, ArrayList<PlayerZone> playerList);
    public void doAction( ArrayList<PlayerZone> playerList);
}
