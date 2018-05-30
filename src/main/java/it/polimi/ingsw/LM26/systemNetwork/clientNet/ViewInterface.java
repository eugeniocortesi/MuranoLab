package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;
import java.util.Observable;

public abstract class ViewInterface extends Observable implements ViewGameInterface {

    public abstract void showNetChoise();

    public abstract void showLoginScreen();

    public abstract void showLoggedScreen();

    public abstract void showAlreadyLoggedScreen();

    public abstract void showTooManyUsersScreen();

}
