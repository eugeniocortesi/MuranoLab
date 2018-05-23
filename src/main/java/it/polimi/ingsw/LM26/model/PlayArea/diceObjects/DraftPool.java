package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import java.util.ArrayList;

public class DraftPool {

    private ArrayList<DieInt> inDraft;

    public DraftPool() {

        this.inDraft = new ArrayList<DieInt>();
    }

    public ArrayList<DieInt> getInDraft() {
        return inDraft;
    }

    public void setInDraft(ArrayList<DieInt> inDraft) {
        this.inDraft = inDraft;
    }
}


