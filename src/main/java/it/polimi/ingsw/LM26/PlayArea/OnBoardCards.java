package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.Cards.ToolCard;

import java.util.ArrayList;

public class OnBoardCards {

    private ArrayList<ObjectivePublicCard> objectivePublicCardList;

    private ArrayList<ToolCard> toolCardList;

    public OnBoardCards(){

        objectivePublicCardList = new ArrayList<ObjectivePublicCard>();

        toolCardList = new ArrayList<ToolCard>();
    }
}
