package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

import java.io.IOException;

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

    public void setOneToken(){

        if (token ==0) token=1;
        //player.token=player.token+1



    }

    public void setTwoToken(){

        token=token +2 ;
        //player.token=player.token+2
    }


    public void play (PlayerZone player){

    }

}
