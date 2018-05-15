package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class Effect5Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect5Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {

        //Die die1 =chooseFromTheDraft();
        //Die die2 =chooseFromRoundTrackTurn();
        //inDraft.add(die2);
        //diceList.add(diceList.index(die2), inDraft.remove(die1));


//NOTA: Creare all'inizio l'oggetto inDraft
        //Creare all'inizio l'oggetto diceList




    }
}
