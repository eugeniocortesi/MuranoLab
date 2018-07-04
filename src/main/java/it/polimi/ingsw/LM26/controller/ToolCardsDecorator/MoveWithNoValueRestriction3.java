package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveWithNoValueRestriction3 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public MoveWithNoValueRestriction3() {
    }

    public MoveWithNoValueRestriction3(ToolCard toolcard) {

        this.toolcard = toolcard;

        this.type="MoveWithNoValueRestriction3";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play (Box fromBox, Box toBox, PlayerZone player) {

        if(fromBox == null ||toBox == null) return false;

        DieInt die = fromBox.getDie();

        if (!fromBox.isIsPresent()) {

            System.out.println("no die found");

            return false;
        }

        if (toBox.isIsPresent()) {

            System.out.println("a die is already present here ");

            return false;
        }

        PlaceDie placement = new PlaceDie(die, toBox, player);

        fromBox.free();

        if (player.getPlayerBoard().getNumDice() == 1) {

            if (placement.checkColorRestriction() && placement.checkEdgeRestrictions()) {

                toBox.setDie(die);

                return true;
            }
        } else if (placement.checkColorRestriction() && placement.checkNearByRestrictions()) {

            toBox.setDie(die);

            return true;
        }

        System.out.println("error card 2");

        fromBox.setDie(die);

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

        this.type="MoveWithNoValueRestriction3";

        this.typeToolCard = "ToolCard";
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
    public boolean play(DieInt dieFromDraft, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList,PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}
