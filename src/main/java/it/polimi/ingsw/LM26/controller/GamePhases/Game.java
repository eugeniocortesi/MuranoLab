package it.polimi.ingsw.LM26.controller.GamePhases;

import java.io.Serializable;


/**
 * Game class
 * @author Eugenio Cortesi
 */

public class Game implements Serializable {

    private PhaseInt phase;


    /**
     * Constructor
     * it creates first game phase and keeps instance of every successive ones
     */

    public Game() {

        this.phase = new InitialPhase();
    }

    public PhaseInt getPhase() {

        return phase;
    }

    public void setPhase(PhaseInt phase){

        this.phase=phase;
    }
}
