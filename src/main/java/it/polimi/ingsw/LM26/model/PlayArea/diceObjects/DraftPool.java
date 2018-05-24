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

    public void printDraftPool(){

        for(int i=0; i<inDraft.size(); i++)
            System.out.print("\t" + inDraft.get(i).toString() + " ");
            System.out.println();
    }
}


