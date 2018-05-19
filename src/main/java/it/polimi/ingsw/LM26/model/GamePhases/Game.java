package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import javafx.print.PageLayout;

import java.util.ArrayList;

public class Game {

    private PhaseInt phase;

    public Game(ArrayList<PlayerZone> playerZones, Decks decks, OnBoardCards onBoardCards) {
        this.phase = new InitialPhase(playerZones, decks, onBoardCards);
    }

    public PhaseInt getPhase(){
        return  phase;
    } //usare getPhase per chiamare i metodi della fase corrente

    public void setPhase(PhaseInt phase){
        this.phase=phase;
    }
}
