package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class MoveTwoDice4 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveTwoDice4(ToolCard toolcard) {
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
        //Die die;

        //for (int i=0; i<2; i++){

        //      fromBox = askTheBox();
        //      toBox = askTheBok();
        //      die = fromBox.getDie();
        //      while(!(placeDie(player, die, toBox))){
        //          System.Out.Println("error " + i+1);
        //          toBox = askTheBok();
        //          }
        //      fromBox.free();
        //}







    }
}
