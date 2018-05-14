package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

public class Effect8Decorator implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public Effect8Decorator(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public void play (PlayerZone player) {


        //while(player.getLastTurn()<1){
        //System.out.println("do first turn first");
        //try {
        //    wait();                                      //chiedi ???
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //}

        //if(plaver.getLastTurn()==1){
        //Die newDie=draw();
        //inDraft.add(newDie);
        //player.setLastTurn(2);
        //}


    }
}
