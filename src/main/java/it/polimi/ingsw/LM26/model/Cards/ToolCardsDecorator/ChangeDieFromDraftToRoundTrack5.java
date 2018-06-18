package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ChangeDieFromDraftToRoundTrack5 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public ChangeDieFromDraftToRoundTrack5() {
    }

    public ChangeDieFromDraftToRoundTrack5(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="ChangeDieFromDraftToRoundTrack5";
        this.typeToolCard = "ToolCard";
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="ChangeDieFromDraftToRoundTrack5";
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
        return toolcard.isInUse();
    }

    @Override
    public void setInUse(boolean inUse) { toolcard.setInUse(inUse); }

    public boolean play(Box fromBox, Box toBox, int player){return false;}

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }
    public boolean play( int player){return false;}

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play (DieInt die1, DieInt die2) {


        Model model = singletonModel();
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();
        int index=0;
        int size=model.getRoundTrackInt().getRoundTrackTurnList().size();
        if(size==0){System.out.println("no dice in roundTrack");
            return false;
        }
        for(int i=0; i<size;i++) {
            ArrayList<DieInt> diceList = model.getRoundTrackInt().getRoundTrackTurnList().get(i).getDiceList();
            index = diceList.indexOf(die2);
            if (index != -1) {
                inDraft.add(inDraft.indexOf(die1), die2);
                diceList.add(diceList.indexOf(die2), die1);
                inDraft.remove(die1);
                diceList.remove(die2);
                model.getRoundTrackInt().dump();
                return true;
            }

        }
        return false;
    }
}
