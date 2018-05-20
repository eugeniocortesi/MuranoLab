package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class RollAllDraftDice7 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public RollAllDraftDice7(ToolCard toolcard) {
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
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}

    public boolean play () {

        //if(player.isDieUsed()=false && player.isSecondTurn=true) {

        //for(int i=0; i<inDraft.size(); i++)
        //inDraft.get(i).roll();
        // }
        //else System.out.println("you can't use this card now")



  //creare oggetto roundTrack
  //ATTENZIONE: isDieUsed va mandato a false ogni volta che un gioctore finisce un turno,
        // va mandato a true ogni volta che un giocatore prende un dado dalla riserva

        return false;
    }
}
