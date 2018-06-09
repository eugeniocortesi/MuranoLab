package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.modelView.ObservableSimple;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class ViewGameInterface extends Observable {

    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    public abstract void startAcceptor(it.polimi.ingsw.LM26.ServerController.Observer observer, ObservableSimple model);

    public abstract void showPrivateCard(String name, ObjectivePrivateCard privateCard);

}
