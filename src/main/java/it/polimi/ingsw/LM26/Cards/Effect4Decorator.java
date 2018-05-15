package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect4Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect4Decorator(ToolCard toolcard) {
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
