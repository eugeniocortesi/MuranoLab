package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.*;

import java.util.ArrayList;

public class OnBoardCards {

    private ArrayList<ObjectivePublicCard>  objectivePublicCardList;

    private ArrayList<ToolCardInt>  toolCardList;

    public OnBoardCards(ArrayList<ObjectivePublicCard> objectivePublicCardList,ArrayList<ToolCardInt> toolCardList) {

        this.objectivePublicCardList = objectivePublicCardList;

        this.toolCardList = toolCardList;

    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {
        return objectivePublicCardList;

    }

    public ArrayList<ToolCardInt> getToolCardList() {
        return toolCardList;
    }
}

//nel main
// public OnBoardCards onBoardCards = new (decks.getObjectivePublicCardDeck(), decks.getToolCardDeck())


