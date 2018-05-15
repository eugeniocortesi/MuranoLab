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
        //while( ! ( (checkColorRestriction(die, toBox) &&  checkNearByRestriction(player, die, toBox))){
        //      System.Out.println("error");
        //      toBox = askTheBok();
        //}
        //toBox.setDie(die);
        //fromBox.free();



    }
}
