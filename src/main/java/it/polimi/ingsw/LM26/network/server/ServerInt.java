package it.polimi.ingsw.LM26.network.server;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;

public interface ServerInt {

    public WindowPatternCard askWhichWindowPatternCard(ArrayList<WindowPatternCard> four);


}
