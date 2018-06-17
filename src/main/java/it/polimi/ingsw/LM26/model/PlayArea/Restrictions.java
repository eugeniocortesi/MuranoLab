package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

public class Restrictions {

    private boolean needPlacement=false;

    private DieInt die=null;

    private boolean firstPart=false;

    private boolean currentPlacement=false;

    public void resetRestrictions(){

        needPlacement=false;

        die=null;

        firstPart=false;

        currentPlacement=false;

    }

    public boolean isNeedPlacement() {
        return needPlacement;
    }

    public void setNeedPlacement(boolean needPlacement) {
        this.needPlacement = needPlacement;
    }

    public DieInt getDie() {
        return die;
    }

    public void setDie(DieInt die) {
        this.die = die;
    }

    public boolean isFirstPart() {
        return firstPart;
    }

    public void setFirstPart(boolean firstPart) {
        this.firstPart = firstPart;
    }

    public boolean isCurrentPlacement() {
        return currentPlacement;
    }

    public void setCurrentPlacement(boolean currentPlacement) {
        this.currentPlacement = currentPlacement;
    }
}
