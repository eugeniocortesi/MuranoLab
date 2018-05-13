package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

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


//NOTA: quando i dadi vengono aggiunti alla inDraft devono gia essere stati rollati



    }
}
