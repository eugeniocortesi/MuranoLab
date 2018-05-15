package it.polimi.ingsw.LM26.GamePhases;

public class Game {

    private PhaseInt phase;

    /*public Game() {
        this.phase = new InitialPhase();
    }*/

    public PhaseInt getPhase(){
        return  phase;
    }

    public void setPhase(PhaseInt phase){
        this.phase=phase;
    }
}
