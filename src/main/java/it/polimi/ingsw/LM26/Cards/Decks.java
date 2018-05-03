package it.polimi.ingsw.LM26.Cards;

import java.util.ArrayList;

public class Decks {

    public ArrayList<ObjectivePublicCard> ObjectivePublicCardDeck;

    public ArrayList<ObjectivePrivateCard> ObjectivePrivateCardDeck;

    public ArrayList<WindowPatternCard> WindowPatternCardDeck;

    public ArrayList<WindowFramePlayerBoard> WindowFramePlayerBoardDeck;

    public ArrayList<ToolCard> ToolCardDeck;

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardDeck() {
        return ObjectivePublicCardDeck;
    }

    public ArrayList<ToolCard> getToolCardDeck() {
        return ToolCardDeck;
    }

    public void setObjectivePublicCardDeck(ArrayList<ObjectivePublicCard> objectivePublicCardDeck) {
        this.ObjectivePublicCardDeck = objectivePublicCardDeck;
    }


    public void setObjectivePrivateCardDeck(ArrayList<ObjectivePrivateCard> objectivePrivateCardDeck) {
        ObjectivePrivateCardDeck = objectivePrivateCardDeck;
    }


    public void setWindowPatternCardDeck(ArrayList<WindowPatternCard> windowPatternCardDeck) {
        WindowPatternCardDeck = windowPatternCardDeck;
    }

    public void setWindowFramePlayerBoardDeck(ArrayList<WindowFramePlayerBoard> windowFramePlayerBoardDeck) {
        WindowFramePlayerBoardDeck = windowFramePlayerBoardDeck;
    }

    public void setToolCardDeck(ArrayList<ToolCard> toolCardDeck) {
        this.ToolCardDeck = toolCardDeck;
    }
}
