package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public interface ControllerInt {

    public boolean check(Die dieFromDraft, Box toBox, int player);

    public boolean check(int twoThree, Box fromBox, Box toBox, int player);
    public boolean check(ToolCard four, Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player);
    public boolean check(ToolCard sixEightNine, Die dieFromDraft, Box toBox, int player);
    public boolean check(ToolCard five, Die dieFromDraft, Die dieFromRoundTrack, int player);
    public boolean check(ToolCard one, Die dieFromDraft, String inDecrement, int player);
    public boolean check(ToolCard tenEleven, Die dieFromDraft, int player);
    public boolean check(ToolCard seven,  int player);
    //ecc.. manca la 12

    public boolean check( String noAction, int player);
}
