package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.CardInt;
import it.polimi.ingsw.LM26.Cards.Decks;
import it.polimi.ingsw.LM26.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.Cards.ToolCard;
import java.util.ArrayList;

public class OnBoardCards {

    private ArrayList<ObjectivePublicCard>  objectivePublicCardList;

    private ArrayList<ToolCard>  toolCardList;

    public OnBoardCards(ArrayList<ObjectivePublicCard> objectivePublicCardList,ArrayList<ToolCard> toolCardList) {

        this.objectivePublicCardList = objectivePublicCardList;

        this.toolCardList = toolCardList;

    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {
        return objectivePublicCardList;

    }

    public ArrayList<ToolCard> getToolCardList() {
        return toolCardList;
    }
}

//nel main
// public OnBoardCards onBoardCards = new (decks.getObjectivePublicCardDeck(), decks.getToolCardDeck())


