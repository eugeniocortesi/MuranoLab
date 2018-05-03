package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.Decks;
import it.polimi.ingsw.LM26.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.Cards.ToolCard;

import java.util.ArrayList;

public class OnBoardCards {

    private Decks objectivePublicCardList;

    private Decks toolCardList;

    public OnBoardCards(Decks objectivePublicCardList, Decks toolCardList) {

        this.objectivePublicCardList = objectivePublicCardList;

        this.toolCardList = toolCardList;
    }
}

//nel main
// public Decks decks = new Decks ()
// public OnBoardCards onBoardCards = new (decks.getObjectivePublicCardDeck(), decks.getToolCardDeck())


