package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.WHITE;

/**
 * PlaceDie class
 * @author Eugenio Cortesi
 * class place a die on the board, if restrictions are respected
 */

public class PlaceDie {

    private DieInt die;

    private Box toBox;

    private PlayerZone player;

    private int i;

    private int j;

    private Box[][] board;

    private static final Logger LOGGER = Logger.getLogger(PlaceDie.class.getName());


    /**
     * Constructor
     * @param dieFromDraft die to place
     * @param toBox destination cell
     * @param player that required the placement
     */

    public PlaceDie(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        this.die = dieFromDraft;

        this.toBox = toBox;

        this.i = toBox.getI();

        this.j = toBox.getJ();

        this.player = player;

        this.board = player.getPlayerBoard().getBoardMatrix();

        LOGGER.setLevel(Level.ALL);
    }


    /**
     * if the board is empty the method call the one for the first placement
     * if the destination cell is already been covered the action is refuse
     * if al restriction al respected the die is placed (linked on this cell to the board)
     * @return final result of the placement action
     */

    public boolean placeDie() {

        if (player.getPlayerBoard().isEmpty()) return placeFirstDie();

        if (toBox.isIsPresent()) {

            LOGGER.log(Level.INFO, "a die is already present here ");

            return false;
        }

        if (checkColorRestriction() && checkValueRestriction() && checkNearByRestrictions()) {

            toBox.setDie(die);

            return true;
        }

        LOGGER.log(Level.INFO, "error in general place die ");

        return false;
    }


    /**
     * the die must be placed on the edge, but event if the coordinates are corrected, all the restrictions must be respected
     * @return result of the first placement
     */

    private boolean placeFirstDie() {

        if (checkEdgeRestrictions()) {

            if (checkColorRestriction() && checkValueRestriction()) {

                toBox.setDie(die);

                player.getPlayerBoard().setEmpty(false);

                return true;

            } else LOGGER.log(Level.INFO, "error in place first die ");

            return false;
        }

        return false;
    }

    public boolean checkEdgeRestrictions() {

        if (!(i == 0 || i == 3 || j == 0 || j == 4)) {

            LOGGER.log(Level.INFO, "error: first die must be placed on the edge");

            return false;
        }

        return true;
    }

    public boolean checkColorRestriction() {

        if (toBox.getPatternBox().isShade() || toBox.getPatternBox().getColor() == die.getColor() || toBox.getPatternBox().getColor() == WHITE) {

            LOGGER.log(Level.INFO, "ok color restriction");

            return true;
        }

        LOGGER.log(Level.INFO, "error in color restriction");

        return false;
    }

    public boolean checkValueRestriction() {

        if (toBox.getPatternBox().isColor() || toBox.getPatternBox().getValue() == die.getValue()) {

            LOGGER.log(Level.INFO, "ok value restriction");

            return true;
        }

        LOGGER.log(Level.INFO, "error in value restriction");

        return false;
    }


    /**
     * every cell on the board is addiacent to from 3 to 8 cells,
     * so the method checks the coordinates of the cell and check if the restrictions are respected for each of the cells near this one.
     * @return result of nearby restrictions
     */

    public boolean checkNearByRestrictions() {

        if (i == 0) {

            if (j == 0) {    //high left corner

                if (checkRightDie()) return false;

                if (checkDownDie()) return false;

                if (checkRight()) return true;

                if (checkDown()) return true;

                if (checkRightDown()) return true;

            } else if (j == 4) {    //high right corner

                if (checkLeftDie()) return false;

                if (checkDownDie()) return false;

                if (checkLeft()) return true;

                if (checkDown()) return true;

                if (checkLeftDown()) return true;

            } else {    //all other cells line first line

                if (checkLeftDie()) return false;

                if (checkRightDie()) return false;

                if (checkDownDie()) return false;

                if (checkLeft()) return true;

                if (checkRight()) return true;

                if (checkDown()) return true;

                if (checkRightDown()) return true;

                if (checkLeftDown()) return true;
            }
        } else if (i == 3) {

            if (j == 0) {    //low left corner

                if (checkUpDie()) return false;

                if (checkRightDie()) return false;

                if (checkUp()) return true;

                if (checkRight()) return true;

                if (checkRightUp()) return true;

            } else if (j == 4) {    //low right corner

                if (checkUpDie()) return false;

                if (checkLeftDie()) return false;

                if (checkUp()) return true;

                if (checkLeft()) return true;

                if (checkLeftUp()) return true;

            } else {    //all other cells line last line

                if (checkUpDie()) return false;

                if (checkRightDie()) return false;

                if (checkLeftDie()) return false;

                if (checkUp()) return true;

                if (checkRight()) return true;

                if (checkLeft()) return true;

                if (checkRightUp()) return true;

                if (checkLeftUp()) return true;
            }
        } else if (j == 0) {    //all other cells in first column

            if (checkUpDie()) return false;

            if (checkRightDie()) return false;

            if (checkDownDie()) return false;

            if (checkUp()) return true;

            if (checkDown()) return true;

            if (checkRight()) return true;

            if (checkRightUp()) return true;

            if (checkRightDown()) return true;

        } else if (j == 4) {    //all other cells in last column

            if (checkDownDie()) return false;

            if (checkLeftDie()) return false;

            if (checkUpDie()) return false;

            if (checkLeft()) return true;

            if (checkDown()) return true;

            if (checkUp()) return true;

            if (checkLeftUp()) return true;

            if (checkLeftDown()) return true;

        } else    //all other inner cells

            if (checkAll()) return true;

        LOGGER.log(Level.INFO, "error in position restriction");

        return false;
    }

    private boolean checkAll() {

        if (checkLeftDie()) return false;

        if (checkRightDie()) return false;

        if (checkDownDie()) return false;

        if (checkUpDie()) return false;

        if (checkLeft()) return true;

        if (checkRight()) return true;

        if (checkDown()) return true;

        if (checkUp()) return true;

        if (checkRightUp()) return true;

        if (checkRightDown()) return true;

        if (checkLeftUp()) return true;

        if (checkLeftDown()) return true;

        return false;
    }

    private boolean checkRight() {

        return (board[i][j + 1].isIsPresent());
    }

    private boolean checkLeft() {

        return (board[i][j - 1].isIsPresent());
    }

    private boolean checkUp() {

        return (board[i - 1][j].isIsPresent());
    }

    private boolean checkDown() {

        return (board[i + 1][j].isIsPresent());
    }

    private boolean checkRightUp() {

        return (board[i - 1][j + 1].isIsPresent());
    }

    private boolean checkRightDown() {

        return (board[i + 1][j + 1].isIsPresent());
    }

    private boolean checkLeftUp() {

        return (board[i - 1][j - 1].isIsPresent());
    }

    private boolean checkLeftDown() {

        return (board[i + 1][j - 1].isIsPresent());
    }


    /**
     * orthogonally dice can't have same value or color
     * @return result from the checks of the die near the cell the client wants to place the die on
     */

    private boolean checkLeftDie() {

        return board[i][j - 1].isIsPresent() && (board[i][j - 1].getDie().getColor().equals(die.getColor()) || board[i][j - 1].getDie().getValue() == die.getValue());
    }

    private boolean checkRightDie() {

        return board[i][j + 1].isIsPresent() && (board[i][j + 1].getDie().getColor().equals(die.getColor()) || board[i][j + 1].getDie().getValue() == die.getValue());
    }

    private boolean checkUpDie() {

        return board[i - 1][j].isIsPresent() && (board[i - 1][j].getDie().getColor().equals(die.getColor()) || board[i - 1][j].getDie().getValue() == die.getValue());
    }

    private boolean checkDownDie() {

        return board[i + 1][j].isIsPresent() && (board[i + 1][j].getDie().getColor().equals(die.getColor()) || board[i + 1][j].getDie().getValue() == die.getValue());
    }
}