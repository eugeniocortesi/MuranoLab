package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class RollAllDraftDice7 implements ToolCardDecorator {

    private ToolCard toolcard = null;

    private String type;

    public RollAllDraftDice7(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="RollAllDraftDice7";
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
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }

    public boolean play (int pl ) {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();

        if(!player.getActionHistory().isDieUsed() && player.getActionHistory().isFirstTurn()==false ) {

            for (int i = 0; i < inDraft.size(); i++)
                inDraft.get(i).roll();
            return true;
        }

        else System.out.println("you can't use this card now");

        return false;

    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }
}
//player.isDieUsed()==false