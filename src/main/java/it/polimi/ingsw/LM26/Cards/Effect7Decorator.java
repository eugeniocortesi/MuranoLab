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

        //if(player.isSecondDie=false && roundTrack.getLastRoundTurn==2)    //????????????????

        //for(int i=0; i<inDraft.size(); i++)
        //inDraft.get(i).roll();



  //creare oggetto roundTrack
  //ATTENZIONE: isSecondDie va mandato a false ogni volta che un gioctore finisce un turno,
        // va mandato a true ogni volta che un giocatore lancia il secondo dado

    }
}
