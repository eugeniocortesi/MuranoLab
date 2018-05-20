package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class MoveWithNoValueRestriction3 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveWithNoValueRestriction3(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2){return false;}
    public boolean play(Die dieFromDraft, Box toBox){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(){return false;}

    public boolean play (Box fromBox,Box toBox)  {

        return false;
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
