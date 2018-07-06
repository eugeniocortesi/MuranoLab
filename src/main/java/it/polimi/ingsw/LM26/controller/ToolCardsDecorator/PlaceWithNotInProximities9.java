package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
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

public class PlaceWithNotInProximities9 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(PlaceWithNotInProximities9.class.getName());

    public PlaceWithNotInProximities9() {
    }

    public PlaceWithNotInProximities9(ToolCard toolcard) {

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type = "PlaceWithNotInProximities9";

        this.typeToolCard = "ToolCard";
    }

    /**
     * method fails if a placement has already been done.
     * if the board is empty it makes sure that the destination cell is on the edge.
     * if the destination cell is near a die placed, the action fails.
     * if every restriction is respected the method directly place the die and update placement-parameters
     * @param dieFromDraft die from draft pool selected by client for the action
     * @param toBox moves die to this board cell
     * @param player of the action
     * @return the success of the card usage
     */

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        Model model = singletonModel();

        if(dieFromDraft == null ||toBox == null) return false;

        Box[][] board = player.getPlayerBoard().getBoardMatrix();

        int i = toBox.getI();

        int j = toBox.getJ();

        PlaceDie placement = new PlaceDie(dieFromDraft, toBox, player);

        if (player.getActionHistory().isPlacement() || player.getActionHistory().isDieUsed()) {

            LOGGER.log(Level.INFO,"action expired");

            return false;
        }

        if (toBox.isIsPresent()) return false;

        if (player.getPlayerBoard().isEmpty()  &&  !(i == 0 || i == 3 || j == 0 || j == 4)) {

                LOGGER.log(Level.INFO,"error: first die must be placed on the edge");

                return false;
            }

        if (placement.checkColorRestriction() && placement.checkValueRestriction()) {

            if (i == 0) {

                if (j == 0) {    //high left corner

                    if (board[i][j + 1].isIsPresent() || board[i + 1][j].isIsPresent() || board[i + 1][j + 1].isIsPresent())

                        return false;
                }

                else if (j == 4) {    //high right corner

                    if (board[i][j - 1].isIsPresent() || board[i + 1][j - i].isIsPresent() || board[i + 1][j].isIsPresent())

                        return false;
                }

                else    //all other cells line first line

                    if (board[i][j - 1].isIsPresent() || board[i + 1][j - 1].isIsPresent() || board[i + 1][j].isIsPresent() ||
                            board[i + 1][j + 1].isIsPresent() || board[i][j + 1].isIsPresent())

                    return false;
            }

            else if (i == 3) {

                if (j == 0) {    //low left corner

                    if (board[i][j + 1].isIsPresent() || board[i - 1][j].isIsPresent() || board[i - 1][j + 1].isIsPresent())

                        return false;

                }

                else if (j == 4) {    //low right corner

                    if (board[i][j - 1].isIsPresent() || board[i - 1][j].isIsPresent() || board[i - 1][j - 1].isIsPresent())

                        return false;
                }

                else    //all other cells line last line

                    if (board[i][j - 1].isIsPresent() || board[i - 1][j - 1].isIsPresent() || board[i - 1][j].isIsPresent() ||
                            board[i - 1][j + 1].isIsPresent() || board[i][j + 1].isIsPresent())

                        return false;
                }

                else {    //all other cells in first column

                if (j == 0) {

                    if (board[i][j + 1].isIsPresent() || board[i - 1][j + 1].isIsPresent() || board[i - 1][j].isIsPresent() ||
                            board[i + 1][j + 1].isIsPresent() || board[i + 1][j].isIsPresent())

                        return false;
                }

                else if (j == 4) {    //all other cells in last column

                    if (board[i][j - 1].isIsPresent() || board[i - 1][j - 1].isIsPresent() || board[i - 1][j].isIsPresent() ||
                            board[i + 1][j - 1].isIsPresent() || board[i + 1][j].isIsPresent())

                        return false;
                }

                else    //all other inner cells

                    if (board[i][j - 1].isIsPresent() || board[i + 1][j - 1].isIsPresent() ||
                        board[i + 1][j].isIsPresent() || board[i + 1][j + 1].isIsPresent() ||
                        board[i][j + 1].isIsPresent() || board[i - 1][j - 1].isIsPresent() ||
                        board[i - 1][j].isIsPresent() || board[i - 1][j + 1].isIsPresent())

                    return false;
            }

            toBox.setDie(dieFromDraft);

            model.getDraftPool().getInDraft().remove(dieFromDraft);

            player.getPlayerBoard().setEmpty(false);

            player.getPlayerBoard().incrementNumDice();

            player.getActionHistory().setDieUsed(true);

            player.getActionHistory().setPlacement(true);

            return true;

        }

        else return false;
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

        this.type = "PlaceWithNotInProximities9";

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