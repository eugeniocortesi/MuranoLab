package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;

public interface ViewGameInterface {

    void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

}
