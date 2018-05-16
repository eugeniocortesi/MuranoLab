package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class DrawOneMoreDie implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public DrawOneMoreDie(ToolCard toolcard) {
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
        //Box toBox = askTheBox();

        //if ( ! placeDie(player, die, toBox)) {
        //          System.Out.Println("error");
        //          }
        // else   inDraft.remove(die);

        //player.setSecondTurn()=true;
        //}


    }
}
