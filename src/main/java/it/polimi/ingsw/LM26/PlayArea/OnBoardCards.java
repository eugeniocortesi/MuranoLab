package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.*;

import java.util.ArrayList;

public class OnBoardCards {

    private ArrayList<ObjectivePublicCard>  objectivePublicCardList;

    private ArrayList<ToolCardInt>  toolCardList;

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

