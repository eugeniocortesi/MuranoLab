package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PlayArea.DieInt;

public class Box {

    private PatternBox patternBox = null;

    private DieInt die=null;

    private boolean isPresent = false;

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
