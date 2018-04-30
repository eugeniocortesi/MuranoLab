package it.polimi.ingsw.LM26.Cards;

import java.util.ArrayList;

public class Decks {

    public ArrayList<ObjectivePublicCard> ObjectivePublicCardDeck;

    public ArrayList<ObjectivePrivateCard> ObjectivePrivateCardDeck;

    public ArrayList<WindowPatternCard> WindowPatternCardDeck;


    public void setObjectivePublicCardDeck(ArrayList<ObjectivePublicCard> objectivePublicCardDeck) {
        this.ObjectivePublicCardDeck = objectivePublicCardDeck;
    }


    public void setObjectivePrivateCardDeck(ArrayList<ObjectivePrivateCard> objectivePrivateCardDeck) {
        ObjectivePrivateCardDeck = objectivePrivateCardDeck;
    }


    public void setWindowPatternCardDeck(ArrayList<WindowPatternCard> windowPatternCardDeck) {
        WindowPatternCardDeck = windowPatternCardDeck;
    }

}
