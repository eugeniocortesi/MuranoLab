package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class DraftPool implements Serializable {

    private ArrayList<DieInt> inDraft;

    public DraftPool() {

        this.inDraft = new ArrayList<DieInt>();
    }

    public ArrayList<DieInt> getInDraft() {
        return inDraft;
    }

    public DieInt get(int i){

        return inDraft.get(i);
    }

    public void setInDraft(ArrayList<DieInt> inDraft) {
        this.inDraft = inDraft;
    }

    public void printDraftPool(){

        for(int i=0; i<inDraft.size(); i++)
            System.out.print("\t" + inDraft.get(i).toString() + " ");
            System.out.println();
    }

    public int size(){
        return inDraft.size();
    }

    //TODO to test

    public void addDie(DieInt d){

        inDraft.add(d);
    }


    public void removeAllDice(){

        int dim= inDraft.size();
        for( int i = 0; i<dim ; i++)
            inDraft.remove(0);
    }

    public void remove(DieInt d){

            inDraft.remove(d);
    }
}


