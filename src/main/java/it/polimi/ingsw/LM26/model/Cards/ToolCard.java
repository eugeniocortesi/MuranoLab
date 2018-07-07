package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ToolCard class
 * @author Eugenio Cortesi
 * this class represent the inner object of the ToolCard, beacuse each card is a decoration on this one.
 */

public class ToolCard extends ToolCardInt {

    private boolean inUse = false;

    private int num;

    private int token = 0;

    transient private static final Logger LOGGER = Logger.getLogger(ToolCard.class.getName());

    public ToolCard() {
    }

    public ToolCard(int num) {

        this.num = num;

        this.typeToolCard = "ToolCard";

        LOGGER.setLevel(Level.ALL);
    }

    /**
     * these two methos are used to make a client pay for the usage of a card
     * it directly takes away token from the client and adds them to the card.
     * of course the game wants that if the card is never used the token to pay is only one.
     * @param player that uses the card
     */

    @Override
    public void setOneToken(PlayerZone player) {

        if (token == 0) token = 1;

        player.getToken().decrementToken();
    }

    @Override
    public void setTwoToken(PlayerZone player) {

        token = token + 2;

        player.getToken().decrementToken();

        player.getToken().decrementToken();
    }

    @Override
    public int getNum() {

        return num;
    }

    @Override
    public boolean isInUse() {

        return inUse;
    }

    @Override
    public void setInUse(boolean inUse) {

        this.inUse = inUse;
    }

    @Override
    public void printCard() {

        LOGGER.log(Level.INFO, " "+ getNum());
    }

    @Override
    public int getToken() {

        return token;
    }

    @Override
    public void rewrite() {

        this.typeToolCard = "ToolCard";
    }


    public boolean play(Box fromBox, Box toBox, PlayerZone player) {

        return false;
    }

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        return false;
    }

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox, PlayerZone player) {

        return false;
    }

    @Override
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack) {

        return false;
    }

    @Override
    public boolean play(DieInt dieFromDraft, String inDeCrement) {

        return false;
    }

    @Override
    public boolean play(DieInt dieFromDraft, PlayerZone pl) {

        return false;
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone pl) {

        return false;
    }

    @Override
    public boolean play(PlayerZone player) {

        return false;
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        return false;
    }
}