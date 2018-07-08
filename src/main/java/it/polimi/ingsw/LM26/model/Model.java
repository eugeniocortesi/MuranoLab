package it.polimi.ingsw.LM26.model;

import com.google.gson.Gson;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.Restrictions;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.model.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;

import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Cards.reloadDecks.loadDecks;
/**
 * Model class
 * @author Eugenio Cortesi
 * class that stores all model objects and structures
 */

public class Model extends ObservableSimple implements Serializable {

    private ArrayList<PlayerZone> playerList;

    private ScoreTrackInt scoreTrackInt;

    private RoundTrackInt roundTrackInt;

    private transient Bag bag;

    private DraftPool draftPool;

    private transient Decks decks;

    private OnBoardCards onBoardCards;

    private transient Restrictions restrictions;


    /**
     * most of the constructor are empty for Gson purpose
     */

    public Model() {
    }


    /**
     * for Tests purposes this reset method permits to create a new Model instance.
     * the method is also called for the normal singleton usage, at the creation of the Model object.
     * Method, as a Constructor, creates objects, areas and structures.
     */

    public void reset() {

        this.roundTrackInt = new RoundTrack("inRoundTrack");

        this.scoreTrackInt = new ScoreTrack("inScoreTrack");

        this.bag = new Bag();

        this.draftPool = new DraftPool("inDraft");

        this.decks = loadDecks();

        this.onBoardCards = new OnBoardCards("onBoardCards");

        this.playerList = new ArrayList<>();

        this.restrictions = new Restrictions();
    }

    public Decks getDecks() {

        return decks;
    }

    public OnBoardCards getOnBoardCards() {

        return onBoardCards;
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

    public Restrictions getRestrictions() {

        return restrictions;
    }

    public void setRestrictions(Restrictions restrictions) {

        this.restrictions = restrictions;
    }

    public void setDecks(Decks decks) {

        this.decks = decks;
    }

    public PlayerZone getPlayer(String name) {

        for (PlayerZone aPlayerList : playerList) {

            if (aPlayerList.getName().equals(name))

                return aPlayerList;
        }

        return null;
    }

    public PlayerZone getPlayer(int id) {

        for (PlayerZone aPlayerList : playerList) {

            if (aPlayerList.getIDPlayer() == id)

                return aPlayerList;
        }

        return null;
    }


    /**
     * model is observed by view
     *
     * @param observer view
     */

    public void setRegister(ObserverSimple observer) {

        this.register(observer);
    }


    /**
     * method that notify observers that it has changed
     */

    public void hasChanged() {

        this.notify(this);
    }

    public synchronized String serializeClassMessage() {

        Gson gson = new Gson();

        return gson.toJson(this, Model.class);
    }

    public synchronized void rewriteBeforeSerializing() {

        for (int i = 0; i < this.getOnBoardCards().getObjectivePublicCardList().size(); i++) {

            this.getOnBoardCards().getObjectivePublicCardList().get(i).getRealEffect().rewrite();
        }

        this.getPlayerList().forEach(PlayerZone::rewrite);

        this.getRoundTrackInt().rewrite();

        this.getScoreTrackInt().rewrite();

        this.onBoardCards.getObjectivePublicCardList().forEach(ObjectivePublicCard::rewrite);

        this.onBoardCards.getToolCardList().forEach(ToolCardInt::rewrite);
    }
}

