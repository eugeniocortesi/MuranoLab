package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.io.Serializable;

/**
 * Box class
 * @author Eugenio Cortesi
 * class creates the object that represents one single cell of the WindowFramePLayerBoard
 */

public class Box implements Serializable {

    private PatternBox patternBox = null;

    private DieInt die = null;

    private boolean isPresent = false;

    private int i = 0, j = 0;

    public Box(){
    }

    public Box(int i, int j) {

        this.i = i;

        this.j = j;
    }

    public int getI() {

        return i;
    }

    public int getJ() {

        return j;
    }

    public void setDie(DieInt die) {

        this.die = die;

        isPresent = true;
    }

    /**
     * dice are positioned on (linked to) a cell of the WindowFramePlayerBoard.
     * the boolean stores the information.
     * @return die linked to the cell
     */

    public DieInt getDie() {

        if (!isPresent) return null;

        return die;
    }

    public void free() {

        isPresent = false;

        die = null;
    }

    public boolean isIsPresent() {

        return isPresent;
    }

    public PatternBox getPatternBox() {

        return patternBox;
    }

    /**
     * method that assigns to each cell of the WindowPatternCard the one with correspondent coordinates from the WindowFramePlayerBoard
     * @param patternBox cell of the WindowPatternCard to link to one of the WindowFramePlayerBoard
     */

    public void setPatternBox(PatternBox patternBox) {

        this.patternBox = patternBox;
    }

    public void rewrite() {

        if(die!=null)

            die.rewrite();
    }
}