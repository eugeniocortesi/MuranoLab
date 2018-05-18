package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public interface ControllerInt {

    public void newAction(Die dieFromDraft, Box toBox, int player);
    public void newAction(ToolCard toolCard,  int player);
    public void newAction( String noAction, int player);
}
