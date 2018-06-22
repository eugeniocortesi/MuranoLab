package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;

public abstract class ViewInterface extends ViewGameInterface implements ObserverSimple {

    public abstract void showNetChoise();

    public abstract void showLoginScreen();

    public abstract void showLoggedScreen();

    public abstract void showAlreadyLoggedScreen();

    public abstract void showTooManyUsersScreen();

    public abstract void showAddedPlayer(String s);

    public abstract void showDisconnectScreen();

    @Override
    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);


}
