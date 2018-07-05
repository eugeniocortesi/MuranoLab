package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ChangeDieWithTheBag11 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public ChangeDieWithTheBag11() {
    }

    public ChangeDieWithTheBag11(ToolCard toolcard) {

        this.toolcard = toolcard;

        this.type="ChangeDieWithTheBag11";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play (DieInt dieFromDraft, PlayerZone player) {

        Model model = singletonModel();

        if(dieFromDraft==null) return false;

        model.getBag().add(dieFromDraft);

        model.getDraftPool().remove(dieFromDraft);

        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {

            System.out.println("action expired");

            return false;
        }

        DieInt d=model.getBag().draw();

        model.getRestrictions().setDie(d);

        if(d.getColor().equals(Color.ANSI_GREEN))

            model.getRestrictions().setColor("Verde");

        if(d.getColor().equals(Color.ANSI_RED))

            model.getRestrictions().setColor("Rosso");

        if(d.getColor().equals(Color.ANSI_PURPLE))

            model.getRestrictions().setColor("Viola");

        if(d.getColor().equals(Color.ANSI_BLUE))

            model.getRestrictions().setColor("Blu");

        if(d.getColor().equals(Color.ANSI_YELLOW))

            model.getRestrictions().setColor("Giallo");

        System.out.println("you got a " + d.getColor() + " die ");

        model.getRestrictions().setFirstPart(true);

        return true;
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone player) {

        Model model = singletonModel();

        if(!model.getRestrictions().isFirstPart())return false;

        if(number<1 || number>6)return false;

        model.getRestrictions().getDie().setRoll(number);

        PlaceDie placement = new PlaceDie(model.getRestrictions().getDie(), toBox, player);

        if (placement.placeDie()) {

            player.getPlayerBoard().incrementNumDice();

            player.getActionHistory().setDieUsed(true);

            player.getActionHistory().setPlacement(true);

            return true;
        }

        model.getDraftPool().addDie(model.getRestrictions().getDie());

        model.getRestrictions().setNeedPlacement(true);

        return false;
    }

    @Override
    public int getNum(){

        return toolcard.getNum();
    }

    @Override
    public void printCard(){

        toolcard.printCard();
    }

    @Override
    public int getToken(){

        return toolcard.getToken();
    }

    @Override
    public void setOneToken(PlayerZone player){

        toolcard.setOneToken(player);
    }

    @Override
    public void setTwoToken(PlayerZone player){

        toolcard.setTwoToken(player);
    }

    @Override
    public boolean isInUse() {

        return toolcard.isInUse();
    }

    @Override
    public void setInUse(boolean inUse) {

        toolcard.setInUse(inUse);
    }

    @Override
    public void rewrite() {

        this.type="ChangeDieWithTheBag11";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play(Box fromBox, Box toBox,  PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox,PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, String inDeCrement) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList,PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}