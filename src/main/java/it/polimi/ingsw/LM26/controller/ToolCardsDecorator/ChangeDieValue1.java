package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

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

public class ChangeDieValue1 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(ChangeDieValue1.class.getName());

    public ChangeDieValue1() {
    }

    public ChangeDieValue1(ToolCard toolcard) {

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type="ChangeDieValue1";

        this.typeToolCard = "ToolCard";
    }

    /**
     *the action in refuse if the value is 1 or 6
     * @param die die to change value of
     * @param inDeCrement action information
     * @return the success of the card usage
     */

    @Override
    public boolean play (DieInt die, String inDeCrement) {

        if( die== null || inDeCrement==null) return false;

        if (inDeCrement.equals("increment")) {

            if (die.getValue() == 6) {

                LOGGER.log(Level.INFO,"error, choose a lower value");

                return false;
            }

            die.increment();

            return true;
        }

        if (inDeCrement.equals("decrement")){

            if (die.getValue() == 1) {

                LOGGER.log(Level.INFO,"error, choose a higher value");

                return false;
            }

            die.decrement();

            return true;
        }

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

        this.type="ChangeDieValue1";

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

        return false;
    }

    @Override
    public boolean play(DieInt dieFromDraft, PlayerZone pl){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone pl){

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
