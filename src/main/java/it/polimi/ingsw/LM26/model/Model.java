package it.polimi.ingsw.LM26.model;

import it.polimi.ingsw.LM26.model.GamePhases.Game;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZoneInt;


import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Model {

    private OnBoardCards onBoardCards;

    private ArrayList<PlayerZone> playerList;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private Bag bag;

    private DraftPool draftPool;

    private Game game;

    public Model() {

        this.roundTrackInt = new RoundTrack();
        this.bag = new Bag();
        this.draftPool =new DraftPool();
        //set playerList, scoreTrack
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public OnBoardCards getOnBoardCards() {
        return onBoardCards;
    }

    public void setOnBoardCards(OnBoardCards onBoardCards) {
        this.onBoardCards = onBoardCards;
    }

    public ArrayList<PlayerZone> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<PlayerZone> playerList) {
        this.playerList = playerList;
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
