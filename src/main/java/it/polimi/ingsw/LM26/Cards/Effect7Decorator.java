package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect7Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect7Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {

        //if(player.isSecondDie=false && roundTrack.getCurrentTurn==2)

        //for(int i=0; i<inDraft.size(); i++)
        //inDraft.get(i).roll();

//oggetto roundTrack ancora da creare



    }
}
