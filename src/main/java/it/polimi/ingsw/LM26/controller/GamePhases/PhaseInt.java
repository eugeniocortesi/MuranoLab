package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;
import java.util.ArrayList;

public interface PhaseInt extends Serializable{

    public void doAction(Game game, ArrayList<PlayerZone> playerList);

    public void nextRound(Round round, Game game);

    public Round getCurrentRound();

    public int[] getTurn();
}

