package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public interface ToolCardInt {

    public int getNum();
    public void printCard();
    /*public int getToken() ;
    public void setOneToken(PlayerZone player);
    public void setTwoToken(PlayerZone player);*/
    public void play(PlayerZone player);
}
