package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class ScorePhase implements PhaseInt{

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

        game.setPhase(new FinalPhase());
    }

    @Override
    public void nextRound(Round round, Game game) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public Round getCurrentRound() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public int[] getTurn() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public PlayerZone getWinner() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void setAllInStandBy(Boolean allInStandBy) {

        throw new UnsupportedOperationException("Not supported here");
    }

}
