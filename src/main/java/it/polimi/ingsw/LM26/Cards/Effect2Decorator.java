package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect2Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect2Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {


        //Box fromBox = askTheBox();
        //Box toBox = askTheBok();
        //Die die = fromBox.getDie();
        //if (checkValueRestriction(fromBox, toBox)==True &&
        //    checkNearByRestriction(player.getWindowPatternCard(),player.getPlayerBoard(), die.getColor(), die.getValue())==True )
        //         toBox.setDie(die);
        //fromBox.free();



    //public boolean checkValueRestriction(Box fromBox, Box toBox){
        //if( toBox.getPatternBox().isColor()) return true;
        // else if (toBox.getPatternBox().getValue()==fromBox.getDie().getValue()) return true;
        //}

        // checkNearByRestriction() controlla che sulla board ci siano dadi addiacenti e che ortogonamente
        // non siano di uguale coloreo valore


    }
}
