package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;

public interface PhaseInt extends Serializable{

    public void doAction(Game game);

    public void nextRound(Round round, Game game);

    public Round getCurrentRound();

    public int[] getTurn();

    public PlayerZone getWinner();

    public void endGame(PlayerZone last);

    public int getNrounds();

    public boolean getOnePlayer();

    public int getRoundNumber();
}

