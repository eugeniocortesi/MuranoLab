package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private PhaseInt phase;

    public Game() {

        this.phase = new InitialPhase();
    }

    public PhaseInt getPhase(){  //usare getPhase per chiamare i metodi della fase corrente

        return  phase;
    }

    public void setPhase(PhaseInt phase){

        this.phase=phase;
    }
}
