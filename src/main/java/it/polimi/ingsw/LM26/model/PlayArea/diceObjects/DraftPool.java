package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DraftPool class
 * @author Chiara Criscuolo
 * It contains all dice that are in the DraftPool
 */

public class DraftPool implements Serializable {

    private ArrayList<DieInt> inDraft;

    private static final Logger LOGGER = Logger.getLogger(Die.class.getName());

    public DraftPool() {
    }

    public DraftPool(String s) {

        this.inDraft = new ArrayList<>();

        LOGGER.setLevel(Level.ALL);
    }

    public ArrayList<DieInt> getInDraft() {

        return inDraft;
    }

    public DieInt get(int i) {

        return inDraft.get(i);
    }

    public void setInDraft(ArrayList<DieInt> inDraft) {
        this.inDraft = inDraft;
    }

    /**
     * Shortcut to print DraftPool
     */

    public void printDraftPool() {

        for (DieInt anInDraft : inDraft) LOGGER.log(Level.INFO, "\t" + anInDraft.toString() + " ");
    }

    public int size() {

        return inDraft.size();
    }

    public void addDie(DieInt d) {

        inDraft.add(d);
    }

    public void removeAllDice() {

        int dim = inDraft.size();

        for (int i = 0; i < dim; i++)

            inDraft.remove(0);
    }

    public void remove(DieInt d) {

        inDraft.remove(d);
    }
}


