package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public interface ToolCardInt {

    public int getNum();
    public void printCard();
    public boolean play(Box fromBox,Box toBox, int player);
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player);
    public boolean play(Die dieFromDraft, Box toBox, int player);
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack);
    public boolean play( Die dieFromDraft, String inDeCrement);
    public boolean play(Die dieFromDraft);
    public boolean play(int player );

    public int getToken();

    public void setOneToken(PlayerZone player);

    public void setTwoToken(PlayerZone player);
}
