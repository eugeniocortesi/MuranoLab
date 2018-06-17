package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class RollAgainADie6 extends ToolCardDecorator {

    private ToolCard toolcard = null;
    private boolean needPlacement=false;
    private DieInt die;

    public RollAgainADie6(ToolCard toolcard) {
        this.toolcard = toolcard;
        this.type="RollAgainADie6";
        this.typeToolCard = "ToolCard";
    }

    public int getNum() {
        return toolcard.getNum();
    }

    @Override
    public void rewrite() {

        this.type="RollAgainADie6";
        this.typeToolCard = "ToolCard";

    }

    public void printCard() {
        toolcard.printCard();
    }

    public int getToken() {
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player) {
    }

    public void setTwoToken(PlayerZone player) {
    }

    @Override
    public boolean isInUse() {
        return toolcard.isInUse();
    }

    @Override
    public void setInUse(boolean inUse) { toolcard.setInUse(inUse); }

    public boolean play(Box fromBox, Box toBox, int player) {
        return false;
    }

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play(DieInt die, Box toBox, int pl) {
        return false;
    }


    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack) {
        return false;
    }

    public boolean play(DieInt dieFromDraft, String inDeCrement) {
        return false;
    }


    public boolean play(int player) {
        return false;
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player) {
        return false;
    }

    public boolean play(DieInt dieFromDraft, int pl) {

        Model model = singletonModel();
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();
        PlayerZone player = model.getPlayerList().get(pl);


        dieFromDraft.roll();
        dieFromDraft.dump();

        if (player.getActionHistory().isDieUsed() || player.getActionHistory().isPlacement())
            System.out.println("you can't place the die");


        else {
            setNeedPlacement(true);
            this.die = dieFromDraft;

        }
        return true;
    }

    @Override
    public boolean play(int number, Box toBox, int pl) {
        return false;
    }

    public DieInt getDieCard6() {
        return die;
    }

    public void removeDie() { this.die = null; }

    public boolean isNeedPlacement() {
        return needPlacement;
    }

    public void setNeedPlacement(boolean needPlacement) {
        this.needPlacement = needPlacement;
    }
}
