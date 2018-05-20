package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class ChangeDieFromDraftToRoundTrack5 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public ChangeDieFromDraftToRoundTrack5(ToolCard toolcard) {
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
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(){return false;}

    public boolean play (Die die1, Die die2) {

        return false;

        //inDraft.add(die2);
        //diceList.add(diceList.index(die2), inDraft.remove(die1));


//NOTA: Creare all'inizio l'oggetto inDraft
        //Creare all'inizio l'oggetto diceList




    }
}
