package it.polimi.ingsw.LM26.model;

import it.polimi.ingsw.LM26.model.GamePhases.Game;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZoneInt;


import java.util.ArrayList;

public class Model {

    private OnBoardCards onBoardCards;

    private ArrayList<PlayerZoneInt> playerZonesInt;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private Bag bag;

    private DraftPool draftPool;

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Model(){
        ;
    }

    public OnBoardCards getOnBoardCards() {
        return onBoardCards;
    }

    public void setOnBoardCards(OnBoardCards onBoardCards) {
        this.onBoardCards = onBoardCards;
    }

    public ArrayList<PlayerZoneInt> getPlayerZonesInt() {
        return playerZonesInt;
    }

    public void setPlayerZonesInt(ArrayList<PlayerZoneInt> playerZonesInt) {
        this.playerZonesInt = playerZonesInt;
    }

    public ScoreTrackInt getScoreTrackInt() {
        return scoreTrackInt;
    }

    public void setScoreTrackInt(ScoreTrackInt scoreTrackInt) {
        this.scoreTrackInt = scoreTrackInt;
    }

    public RoundTrackInt getRoundTrackInt() {
        return roundTrackInt;
    }

    public void setRoundTrackInt(RoundTrackInt roundTrackInt) {
        this.roundTrackInt = roundTrackInt;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public DraftPool getDraftPool() {
        return draftPool;
    }

    public void setDraftPool(DraftPool draftPool) {
        this.draftPool = draftPool;
    }
}
