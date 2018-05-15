package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

public class Box {

    private PatternBox patternBox = null;

    private DieInt die=null;

    private boolean isPresent = false;

    private int i=0, j=0;

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
        isPresent=true;
    }

    public DieInt getDie() {
        if(isPresent==false)return null;
        return die;
    }

    public void free(){
        isPresent=false;
        die=null;
    }

    public boolean isIsPresent() {

        return isPresent;
    }

    public PatternBox getPatternBox() {
        return patternBox;
    }

    public void setPatternBox(PatternBox patternBox) {
        this.patternBox = patternBox;
    }
}
