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

public class MoveTwoDice4 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(MoveTwoDice4.class.getName());

    public MoveTwoDice4() {
    }

    public MoveTwoDice4(ToolCard toolcard) {

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type = "MoveTwoDice4";

        this.typeToolCard = "ToolCard";
    }

    /**
     * if dice are already present in the destination cells or if in the starting cells there aren't the action fails
     * if just one of the two placement fails, the other is undone and the action fails
     *
     * @param fromBoxList list of cells from which dice are moved
     * @param toBoxList   list of cells towards to dice are moved
     * @param player      of the action
     * @return the success of the card usage
     */

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        if (fromBoxList == null || toBoxList == null) return false;

        ArrayList<DieInt> dieList = new ArrayList<>();

        for (int i = 0; i < fromBoxList.size(); i++)

            if (!fromBoxList.get(i).isIsPresent() || toBoxList.get(i).isIsPresent()) {

                for (int k = 0; k < dieList.size(); k++) {

                    fromBoxList.get(k).setDie(dieList.get(k));

                    toBoxList.get(k).free();
                }

                return false;
            } else {
                dieList.add(fromBoxList.get(i).getDie());

                fromBoxList.get(i).free();
            }

        for (int j = 0; j < fromBoxList.size(); j++) {

            PlaceDie placement = new PlaceDie(dieList.get(j), toBoxList.get(j), player);

            if (!placement.placeDie()) {

                LOGGER.log(Level.INFO, "error " + j + " placement");

                for (int k = 0; k < fromBoxList.size(); k++) {

                    fromBoxList.get(k).setDie(dieList.get(k));

                    toBoxList.get(k).free();
                }

                return false;
            }
        }

        return true;
    }

    @Override
    public int getNum() {

        return toolcard.getNum();
    }

    @Override
    public void printCard() {

        toolcard.printCard();
    }

    @Override
    public int getToken() {

        return toolcard.getToken();
    }

    @Override
    public void setOneToken(PlayerZone player) {

        toolcard.setOneToken(player);
    }

    @Override
    public void setTwoToken(PlayerZone player) {

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

        this.type = "MoveTwoDice4";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play(Box fromBox, Box toBox, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox, PlayerZone player) {

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
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}