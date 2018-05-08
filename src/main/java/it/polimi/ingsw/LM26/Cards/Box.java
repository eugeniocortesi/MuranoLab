package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PlayArea.DieInt;

public class Box {

    private DieInt die;

    private boolean isPresent = false;

    public Box() {

        if (isPresent == false) ;

    }

    public void setDie(DieInt die) {
        this.die = die;
        isPresent=true;
    }

    public DieInt getDie() {
        if(isPresent==false)return null;
        return die;
    }

    public boolean isIsPresent() {
        return isPresent;
    }
}
