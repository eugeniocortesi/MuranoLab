package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public interface ToolCardInt {

    public int getNum();
    public void printCard();
    public void play (PlayerZone player);
}
