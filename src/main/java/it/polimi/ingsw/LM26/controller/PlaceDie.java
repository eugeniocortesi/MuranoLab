package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

public class PlaceDie implements ActionDecorator{

    /*public boolean checkWindowPatternCard(DieInt d, WindowPatternCard windowPatternCard, int x, int y){

        //.....window.box(x)(Y) = null
        return false;
    }*/

    private PlayerActionInt newAction=null;

    public PlaceDie(PlayerActionInt newAction) {

        this.newAction=newAction;

    }

    public void check(){

    }
}
