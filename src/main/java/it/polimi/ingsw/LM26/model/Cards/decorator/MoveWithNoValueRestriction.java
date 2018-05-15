package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class MoveWithNoValueRestriction implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveWithNoValueRestriction(ToolCard toolcard) {
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
