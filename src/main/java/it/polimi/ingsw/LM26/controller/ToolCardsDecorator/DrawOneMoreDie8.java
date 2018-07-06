package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;


/**
 * ToolCard decorator class
 * @author Eugenio Cortesi
 */

public class DrawOneMoreDie8 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(DrawOneMoreDie8.class.getName());

    public DrawOneMoreDie8() {
    }

    public DrawOneMoreDie8(ToolCard toolcard) {

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type="DrawOneMoreDie8";

        this.typeToolCard = "ToolCard";
    }

    /**
     * the card lets the player place one more die after his first action.
     * the method has to control that client is in his first turn, otherwise it refuse the action.
     * if he is, the card is 'used', but the action will occur at the end of the client turn: he will send another placement-action.
     * the information is stored in model-restrictions.
     * the information that stores in the client that he can't do the second turn is set after the placement of the occurred.
     * these are the action combinations possible in a whole round for the who use this card:
     * - placement, card, placement
     * - card, placement, placement
     * - card, pass, placement
     * @param player of the action
     * @return the success of the card usage
     */

    @Override
    public boolean play ( PlayerZone player) {

        Model model = singletonModel();

        if(player.getActionHistory().isFirstTurn()  &&  !player.getActionHistory().isSecondTurn()) {

            model.getRestrictions().setTool8needPlacement(true);

            return true;
        } else

            LOGGER.log(Level.INFO,"you can't use this card in second turn ");

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

        this.type="DrawOneMoreDie8";

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
    public boolean play(DieInt dieFromDraft, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList,PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}
