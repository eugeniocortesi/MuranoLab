package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class ToolCard implements ToolCardInt  {

    private boolean inUse=false;
    private int num;
    private int token=0;

    public ToolCard(int num) {

        this.num=num;

    }

    public int getNum() {

        return num;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
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


    public boolean play(Box fromBox,Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(DieInt dieFromDraft, Box toBox, int player ){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }
    public boolean play(int player){return false;}


}
