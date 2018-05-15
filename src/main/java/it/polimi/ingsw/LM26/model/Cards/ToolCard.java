package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class ToolCard implements ToolCardInt  {

    //private String title= null;
    private int num;
    private int token=0;

    public ToolCard(int num) {

        this.num=num;

    }

    public int getNum() {

        return num;
    }

    public void printCard(){

        System.out.println(getNum());

    }

    public int getToken() {
        return token;
    }

    public void setOneToken(PlayerZone player){

        if (token ==0) token=1;
        player.getToken().decrementToken();

    }

    public void setTwoToken(PlayerZone player){

        token=token +2 ;
        player.getToken().decrementToken();
        player.getToken().decrementToken();
    }


    public void play (PlayerZone player){

    }

}
