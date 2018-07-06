package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * ToolCard decorator class
 * @author Eugenio Cortesi
 */

public class MoveWithNoColorRestriction2 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(MoveWithNoColorRestriction2.class.getName());

    public MoveWithNoColorRestriction2() {
    }

    public MoveWithNoColorRestriction2(ToolCard toolcard){

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type="MoveWithNoColorRestriction2";

        this.typeToolCard = "ToolCard";
    }


    /**
     * action is refuse if the placement don't respect restrictions and if there is no die on the cell selected
     * if is the only die on the board it must be placed again on the edges.
     * @param fromBox move die from this board cell
     * @param toBox moves die to this board cell
     * @param player of the action
     * @return the success of the card usage
     */

    @Override
    public boolean play (Box fromBox, Box toBox, PlayerZone player) {

        if(fromBox == null ||toBox == null) return false;

        DieInt die = fromBox.getDie();

        if(!fromBox.isIsPresent()){

            LOGGER.log(Level.INFO,"no die found");

            return false;
        }

        if(toBox.isIsPresent()){

            LOGGER.log(Level.INFO,"a die is already present here ");

            return false;
        }

        PlaceDie placement = new PlaceDie(die, toBox, player);

        fromBox.free();

        if(player.getPlayerBoard().getNumDice()==1) {

            if (placement.checkValueRestriction() && placement.checkEdgeRestrictions()) {

                toBox.setDie(die);

                return true;
            }
        }

        else if (placement.checkValueRestriction() && placement.checkNearByRestrictions() ){

            toBox.setDie(die);

            return true;
        }

        LOGGER.log(Level.INFO,"error card 2");

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

    /**
     * method that rewrite type for serializing with gson
     */

    @Override
    public void rewrite() {

        this.type="MoveWithNoColorRestriction2";

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

