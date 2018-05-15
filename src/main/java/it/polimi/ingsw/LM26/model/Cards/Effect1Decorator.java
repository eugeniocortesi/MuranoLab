package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class Effect1Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect1Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {


        //String action=askToIncrementOrDecrement();
        //Die die=chooseFromTheDraft();


        //if (action=="increment")
        //      while(die.getValue()==6){
        //              System.out.println("error, choose a lower value"
        //              die=chooseFromTheDraft();
                        //}
        //      die.increment();

        //if (action=="decrement")
        //      while(die.getValue()==1){
        //            System.out.println("error, choose a higher value"
        //               die=chooseFromTheDraft();
        //       die.decrement();





    }
}
