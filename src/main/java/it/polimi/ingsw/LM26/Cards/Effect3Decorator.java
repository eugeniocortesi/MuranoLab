package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect3Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect3Decorator(ToolCard toolcard) {
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
        //if (checkColorRestriction(fromBox, toBox)==True &&
        //    checkNearByRestriction(player.getWindowPatternCard(),player.getPlayerBoard(), die.getColor(), die.getValue())==True )
        //         toBox.setDie(die);
        //fromBox.free();

//public boolean checkColorRestriction(Box fromBox, Box toBox){
        //if( toBox.getPatternBox().isValue()) return true;
        // else if (toBox.getPatternBox().getColor()==fromBox.getDie().getColor() || toBox.getPatternBox().getColor()==WHITE) return true;
        //}

    }
}
