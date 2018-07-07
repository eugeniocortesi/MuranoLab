package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ToolCard abstract class
 * @author Eugenio Cortesi
 */

public abstract class ToolCardInt implements Serializable {

    protected String typeToolCard;

    public ToolCardInt() {
    }

    public abstract int getToken();

    public abstract void setOneToken(PlayerZone player);

    public abstract void setTwoToken(PlayerZone player);

    public abstract boolean isInUse();

    public abstract void setInUse(boolean inUse);

    public abstract int getNum();

    public abstract void rewrite();

    public abstract void printCard();

    public abstract boolean play(Box fromBox, Box toBox, PlayerZone player);

    public abstract boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player);

    public abstract boolean play(DieInt dieFromDraft, Box toBox, PlayerZone player);

    public abstract boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack);

    public abstract boolean play(DieInt dieFromDraft, String inDeCrement);

    public abstract boolean play(DieInt dieFromDraft, PlayerZone player);

    public abstract boolean play(int number, Box toBox, PlayerZone player);

    public abstract boolean play(PlayerZone player);

    public abstract boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player);
}