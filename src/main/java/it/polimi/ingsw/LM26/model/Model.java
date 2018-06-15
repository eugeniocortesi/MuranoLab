package it.polimi.ingsw.LM26.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Cards.*;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.*;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZoneInt;
import it.polimi.ingsw.LM26.model.Serialization.*;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class Model extends ObservableSimple implements Serializable {



    private ArrayList<PlayerZone> playerList;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private Bag bag;

    private DraftPool draftPool;

    //private Game game;

    private transient Decks decks;

    private OnBoardCards onBoardCards;

    public Model() {

        this.roundTrackInt = new RoundTrack();
        this.scoreTrackInt = new ScoreTrack();
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

    /*public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }*/

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

    public void hasChanged(){
        this.notify(this);
    }

    public synchronized String serializeClassMessage(){

        /*RuntimeTypeAdapterFactory1<RoundTrackInt> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1.of(RoundTrackInt.class, "type")
                .registerSubtype(RoundTrack.class, "RoundTrack");
        RuntimeTypeAdapterFactory1<ScoreTrackInt> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory1.of(ScoreTrackInt.class, "typeScoreTrack")
                .registerSubtype(ScoreTrack.class, "ScoreTrack");
        RuntimeTypeAdapterFactory1<DieInt> runtimeTypeAdapterFactory3 = RuntimeTypeAdapterFactory1.of(DieInt.class, "type")
                .registerSubtype(Die.class, "Die");
        RuntimeTypeAdapterFactory1<ToolCardInt> runtimeTypeAdapterFactory4 = RuntimeTypeAdapterFactory1.of(ToolCardInt.class, "type")
                .registerSubtype(ToolCardInt.class, "ToolCard");

        RuntimeTypeAdapterFactory1<ToolCardDecorator> runtimeTypeAdapterFactory5 = RuntimeTypeAdapterFactory1.of(ToolCardDecorator.class, "type")
            .registerSubtype(ChangeDieWithTheBag11.class, "ChangeDieFromTheBag11")
            .registerSubtype(ChangeDieFromDraftToRoundTrack5.class, "ChangeDieFromDraftToRoundTrack5")
            .registerSubtype(ChangeDieValue1.class, "ChangeDieValue1")
            .registerSubtype(DrawOneMoreDie8.class, "DrawOneMoreDie8")
            .registerSubtype(MoveTwoDice4.class, "MoveTwoDice4")
            .registerSubtype(MoveTwoDiceWithSameColor12.class, "MoveTwoDiceWithTheSameColor12")
            .registerSubtype(MoveWithNoColorRestriction2.class, "MoveWithNoColorRestriction2")
            .registerSubtype(MoveWithNoValueRestriction3.class, "MoveWithNoValueRestriction3")
            .registerSubtype(PlaceWithNotInProximities9.class, "PlaceWithNotInProximities9")
            .registerSubtype(RollAgainADie6.class, "RollAgainADie6")
            .registerSubtype(RollAllDraftDice7.class, "RollAllDraftDice7")
            .registerSubtype(RollToTheOppositeFace10.class, "RollToTheOppositeFace10");

        RuntimeTypeAdapterFactory1<PlayerZoneInt> runtimeTypeAdapterFactory6 = RuntimeTypeAdapterFactory1.of(PlayerZoneInt.class, "type")
                .registerSubtype(PlayerZone.class, "PlayerZone");

        RuntimeTypeAdapterFactory1<CardInt> runtimeTypeAdapterFactory7 = RuntimeTypeAdapterFactory1.of(CardInt.class, "type")
                .registerSubtype(ObjectivePrivateCard.class, "ObjectivePrivateCard")
                .registerSubtype(ObjectivePublicCard.class, "ObjectivePublicCard")
                .registerSubtype(WindowPatternCard.class, "WindowPatternCard");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory2)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory3)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory4)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory5)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory6)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory7)
                .create();*/
        Gson gson = new Gson();

        String msgJson = gson.toJson(this, Model.class);
        return msgJson;
    }

    static public Model deserializeModelMessage(String protocolJson){

        RuntimeTypeAdapterFactory1<Effect> runtimeTypeAdapterFactory8 = RuntimeTypeAdapterFactory1
                .of(Effect.class, "type")
                .registerSubtype(DifferentColorShadeOnRowColomn.class)
                .registerSubtype(DifferentColorShade.class)
                .registerSubtype(Shades.class)
                .registerSubtype(ColoredDiagonals.class);

        RuntimeTypeAdapterFactory1<RoundTrackInt> runtimeTypeAdapterFactory1 = RuntimeTypeAdapterFactory1.of(RoundTrackInt.class, "type")
                .registerSubtype(RoundTrack.class, "RoundTrack");
        RuntimeTypeAdapterFactory1<ScoreTrackInt> runtimeTypeAdapterFactory2 = RuntimeTypeAdapterFactory1.of(ScoreTrackInt.class, "typeScoreTrack")
                .registerSubtype(ScoreTrack.class, "ScoreTrack");
        RuntimeTypeAdapterFactory1<DieInt> runtimeTypeAdapterFactory3 = RuntimeTypeAdapterFactory1.of(DieInt.class, "type")
                .registerSubtype(Die.class, "Die");
        RuntimeTypeAdapterFactory1<ToolCardInt> runtimeTypeAdapterFactory4 = RuntimeTypeAdapterFactory1.of(ToolCardInt.class, "typeToolCard")
                .registerSubtype(ToolCard.class, "ToolCard")
                .registerSubtype(ToolCardDecorator.class, "ToolCardDecorator");

        RuntimeTypeAdapterFactory1<ToolCardDecorator> runtimeTypeAdapterFactory5 = RuntimeTypeAdapterFactory1.of(ToolCardDecorator.class, "type")
            .registerSubtype(ChangeDieWithTheBag11.class, "ChangeDieFromTheBag11")
            .registerSubtype(ChangeDieFromDraftToRoundTrack5.class, "ChangeDieFromDraftToRoundTrack5")
            .registerSubtype(ChangeDieValue1.class, "ChangeDieValue1")
            .registerSubtype(DrawOneMoreDie8.class, "DrawOneMoreDie8")
            .registerSubtype(MoveTwoDice4.class, "MoveTwoDice4")
            .registerSubtype(MoveTwoDiceWithSameColor12.class, "MoveTwoDiceWithTheSameColor12")
            .registerSubtype(MoveWithNoColorRestriction2.class, "MoveWithNoColorRestriction2")
            .registerSubtype(MoveWithNoValueRestriction3.class, "MoveWithNoValueRestriction3")
            .registerSubtype(PlaceWithNotInProximities9.class, "PlaceWithNotInProximities9")
            .registerSubtype(RollAgainADie6.class, "RollAgainADie6")
            .registerSubtype(RollAllDraftDice7.class, "RollAllDraftDice7")
            .registerSubtype(RollToTheOppositeFace10.class, "RollToTheOppositeFace10");


        RuntimeTypeAdapterFactory1<PlayerZoneInt> runtimeTypeAdapterFactory6 = RuntimeTypeAdapterFactory1.of(PlayerZoneInt.class, "type")
                .registerSubtype(PlayerZone.class, "PlayerZone");

        RuntimeTypeAdapterFactory1<CardInt> runtimeTypeAdapterFactory7 = RuntimeTypeAdapterFactory1.of(CardInt.class, "type")
                .registerSubtype(ObjectivePrivateCard.class, "ObjectivePrivateCard")
                .registerSubtype(ObjectivePublicCard.class, "ObjectivePublicCard")
                .registerSubtype(WindowPatternCard.class, "WindowPatternCard");

        Type type = new TypeToken<Model>(){}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory1)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory2)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory3)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory4)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory5)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory6)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory7)
                .registerTypeAdapterFactory(runtimeTypeAdapterFactory8)
                .create();


        Model model= gson.fromJson(protocolJson, type);
        System.out.println("playerlist "+ model.getPlayerList());
        for(int i = 0; i<model.getPlayerList().size(); i++){
            System.out.println(model.getPlayerList().get(i)+ "player");
        }

        return model;
    }

    public synchronized void rewriteBeforeSerializing(){

        for(int i=0; i<this.getOnBoardCards().getObjectivePublicCardList().size(); i++){

            this.getOnBoardCards().getObjectivePublicCardList().get(i).getRealEffect().rewrite();
        }

        this.getDecks().getToolCardDeck().forEach(toolCard -> {toolCard.rewrite();});
        this.getDecks().getWindowPatternCardDeck().forEach(windowPatternCard ->  {windowPatternCard.rewrite();});
        this.getDecks().getObjectivePrivateCardDeck().forEach( privateCard -> {privateCard.rewrite();});
        this.getDecks().getObjectivePublicCardDeck().forEach( publicCard -> {publicCard.rewrite();});
        this.getPlayerList().forEach(player -> {player.rewrite();});
        this.getRoundTrackInt().rewrite();
        this.getScoreTrackInt().rewrite();
        this.onBoardCards.getObjectivePublicCardList().forEach( cards -> {cards.rewrite();});
        this.onBoardCards.getToolCardList().forEach( cards -> {cards.rewrite();});


    }
}

