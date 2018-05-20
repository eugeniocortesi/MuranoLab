package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class ChangeDieValue1 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public ChangeDieValue1(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public boolean play(Box fromBox, Box toBox){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2){return false;}
    public boolean play(Die dieFromDraft, Box toBox){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(){return false;}

    public boolean play (Die dieFromDraft, String inDeCrement) {


        return false;

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
