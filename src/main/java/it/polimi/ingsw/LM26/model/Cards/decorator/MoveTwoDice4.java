package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
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

    public boolean play(Box fromBox, Box toBox){return false;}
    public boolean play(Die dieFromDraft, Box toBox){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(){return false;}

    public boolean play (Box fromBox1, Box toBox1, Box fromBox2, Box toBox2) {

        return false;


        //        //
        //        //      die = fromBox1.getDie();
        //        //      while(!(placeDie(player, die, toBox1))){
        //        //          System.Out.Println("error " + 1);
                //              return null
        //        //
        //        //          }
        //        //      fromBox1.free();



        //        //      die = fromBox2.getDie();
        //        //      while(!(placeDie(player, die, toBox2))){
        //        //          System.Out.Println("error " + 2);
        //        //          return null
        //        //          }
        //        //      fromBox2.free();





    }
}
