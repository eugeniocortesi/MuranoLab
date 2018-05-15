package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class Effect8Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect8Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {



        //if(!plaver.isSecondTurn() ){
        //Die die=chooseFromTheDraft();
        //Box fromBox = askTheBox();

        //if ( ! placeDie(player, die, toBox)) {
        //          System.Out.Println("error");
        //          }
        // else   inDraft.remove(die);

        //player.setSecondTurn()=true;
        //}


    }
}
