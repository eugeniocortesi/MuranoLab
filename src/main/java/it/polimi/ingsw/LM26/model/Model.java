package it.polimi.ingsw.LM26.model;

import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.modelView.ObserverSimple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class Model extends ObservableSimple implements Serializable {

    private OnBoardCards onBoardCards;

    private ArrayList<PlayerZone> playerList;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private Bag bag;

    private DraftPool draftPool;

    private Game game;

    private Decks decks;

    private List<Observer> list = new ArrayList<Observer>();

    public Model() {

        this.roundTrackInt = new RoundTrack();
        this.bag = new Bag();
        this.draftPool =new DraftPool();
        this.onBoardCards= new OnBoardCards();
        this.decks=singletonDecks();
        this.playerList=new ArrayList<PlayerZone>();
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

    public PlayerZone getPlayer(String name){
        for(int i=0; i<playerList.size(); i++){
            if(playerList.get(i).getName().equals(name))
                return playerList.get(i);
        }
        return null;
    }

    public PlayerZone getPlayer(int id){
        for(int i=0; i<playerList.size(); i++){
            if(playerList.get(i).getIDPlayer()== id)
                return playerList.get(i);
        }
        return null;
    }

    public void setRegister(ObserverSimple observer){
        this.register(observer);
    }

    public void notifyObservers(){
        this.notify(this);
    }

    public void hasChanged(){
        notifyObservers();
    }
}

