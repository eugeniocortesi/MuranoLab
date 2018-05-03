package it.polimi.ingsw.LM26.Cards;

import java.util.ArrayList;

public class Decks {

    public ArrayList<CardInt> ObjectivePublicCardDeck;

    private ArrayList<CardInt> ObjectivePrivateCardDeck;

    public ArrayList<CardInt> WindowPatternCardDeck;

    public ArrayList<CardInt> WindowFramePlayerBoardDeck;

    public ArrayList<CardInt> ToolCardDeck;

    public ArrayList<CardInt> getObjectivePublicCardDeck() {
        return ObjectivePublicCardDeck;
    }

    public ArrayList<CardInt> getToolCardDeck() {
        return ToolCardDeck;
    }

    public void setObjectivePublicCardDeck(ArrayList<CardInt> objectivePublicCardDeck) {
        this.ObjectivePublicCardDeck = objectivePublicCardDeck;
    }


    public void setObjectivePrivateCardDeck(ArrayList<CardInt> objectivePrivateCardDeck) {
        ObjectivePrivateCardDeck = objectivePrivateCardDeck;
    }


    public void setWindowPatternCardDeck(ArrayList<CardInt> windowPatternCardDeck) {
        WindowPatternCardDeck = windowPatternCardDeck;
    }

    public void setWindowFramePlayerBoardDeck(ArrayList<CardInt> windowFramePlayerBoardDeck) {
        WindowFramePlayerBoardDeck = windowFramePlayerBoardDeck;
    }

    public void setToolCardDeck(ArrayList<CardInt> toolCardDeck) {
        this.ToolCardDeck = toolCardDeck;
    }
}
