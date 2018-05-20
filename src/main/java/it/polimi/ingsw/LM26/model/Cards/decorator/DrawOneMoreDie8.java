package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class DrawOneMoreDie8 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public DrawOneMoreDie8(ToolCard toolcard) {
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
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(){return false;}

    public boolean play (Die dieFromDraft, Box toBox) {


        return false;
        //if(!plaver.isSecondTurn() ){
        //Die die=chooseFromTheDraft();
        //Box toBox = askTheBox();

        // ANCHE PIAZZAMENTO?

        //if ( ! placeDie(player, die, toBox)) {
        //          System.Out.Println("error");
        //          }
        // else   inDraft.remove(die);

        //player.setSecondTurn()=true;
        //}


    }
}
