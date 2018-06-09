package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;

public interface ToolCardInt extends Serializable{

    public int getNum();
    public void printCard();
    public boolean play(Box fromBox,Box toBox, int player);
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player);
    public boolean play(DieInt dieFromDraft, Box toBox, int player);
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack);
    public boolean play( DieInt dieFromDraft, String inDeCrement);
    public boolean play(DieInt dieFromDraft, int pl);
    public boolean play(int player );

    public int getToken();

    public void setOneToken(PlayerZone player);

    public void setTwoToken(PlayerZone player);

    public boolean isInUse();

    public void setInUse(boolean inUse);

}
