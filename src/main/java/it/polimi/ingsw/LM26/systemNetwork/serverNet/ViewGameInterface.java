package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ViewGameInterface extends Observable {

    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    public abstract void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model);

    public abstract void showPrivateCard(String name, ObjectivePrivateCard privateCard);

    public abstract void showSetPlayerMenu(String name, PlayerZone player);

    public abstract void showCurrentMenu(String name);

}
