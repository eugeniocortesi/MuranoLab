package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class ScorePhase implements PhaseInt{

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

        game.setPhase(new FinalPhase());
    }
}
