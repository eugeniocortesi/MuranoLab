package it.polimi.ingsw.LM26.model;

import it.polimi.ingsw.LM26.controller.Update.Update;
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
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Model extends Observable{

    private OnBoardCards onBoardCards;

    private ArrayList<PlayerZone> playerList;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private Bag bag;

    private DraftPool draftPool;

    private Game game;

    private Decks decks;

    public Model() {

        this.roundTrackInt = new RoundTrack();
        this.bag = new Bag();
        this.draftPool =new DraftPool();
        this.onBoardCards= new OnBoardCards();
        this.decks=singletonDecks();
        //set playerList, scoreTrack
    }

    /*public void accept(Update update){

        //check istanceof Update

    }*/

    public Decks getDecks() { return decks; }

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

    @Override

    public void notifyObservers() {

        super.notifyObservers();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged();
    }
}
