package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;
import java.util.ArrayList;

public interface ToolCardInt extends Serializable{

    public int getNum();
    public void printCard();
    public boolean play(Box fromBox,Box toBox, int player);
    public boolean play( ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player);
    public boolean play(DieInt dieFromDraft, Box toBox, int player);
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack);
    public boolean play( DieInt dieFromDraft, String inDeCrement);
    public boolean play(DieInt dieFromDraft, int pl);
    public boolean play(int number, Box toBox, int pl);
    public boolean play(int player );
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, int player);

    public int getToken();

    public void setOneToken(PlayerZone player);

    public void setTwoToken(PlayerZone player);

    public boolean isInUse();

    public void setInUse(boolean inUse);

}
