package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;

import java.util.ArrayList;

public class OnBoardCards {

    private ArrayList<ObjectivePublicCard>  objectivePublicCardList;

    private ArrayList<ToolCardInt>  toolCardList;

    public OnBoardCards() {

        this.objectivePublicCardList =new ArrayList<ObjectivePublicCard>() ;

        this.toolCardList = new ArrayList<ToolCardInt>();
    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {
        return objectivePublicCardList;
    }

    public ArrayList<ToolCardInt> getToolCardList() {
        return toolCardList;
    }

    public void setObjectivePublicCardList(ArrayList<ObjectivePublicCard> objectivePublicCardList) {
        this.objectivePublicCardList = objectivePublicCardList;
    }

    public void setToolCardList(ArrayList<ToolCardInt> toolCardList) {
        this.toolCardList = toolCardList;
    }
}

