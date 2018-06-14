package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class RollToTheOppositeFace10 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public RollToTheOppositeFace10(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="RollToTheOppositeFace10";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="RollToTheOppositeFace10";
        this.typeToolCard = "ToolCard";

    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){}

    public void setTwoToken(PlayerZone player){}

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public void setInUse(boolean inUse) {

    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play(DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(int player){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play (DieInt dieFromDraft, int pl) {

        //6  1
        //5  2
        //4  3

        int count=0;
        int val=0;
        if(dieFromDraft.getValue()==1 || dieFromDraft.getValue()==4)val=5;
        if(dieFromDraft.getValue()==2 || dieFromDraft.getValue()==5)val=3;
        if(dieFromDraft.getValue()==3 || dieFromDraft.getValue()==6)val=1;

        for(int i=0; i<val; i++)
            dieFromDraft.increment();

        dieFromDraft.dump();

    return true ;
    }

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }

}
