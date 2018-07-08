package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;

/**
 * Abstract class that is implemented by View
 */

public abstract class ViewInterface extends ViewGameInterface implements ObserverSimple {

    /**
     * method used to choose the type of net
     */

    public abstract void showNetChoise();

    /**
     * method used to do the request of login
     */

    public abstract void showLoginScreen();

    /**
     * Method that answer the result after the login phase
     * Logged successfully or not
     */

    public abstract void showLoggedScreen();

    /**
     * Method that says that already one was logged with the user inserted in login phase
     */

    public abstract void showAlreadyLoggedScreen();

    /**
     * Method that says that already a game is going on
     */

    public abstract void showTooManyUsersScreen();

    /**
     * Method that notify players that another one joins the game
     * @param s name of player that joins the game
     */

    public abstract void showAddedPlayer(String s);

    /**
     * Method that says that the player is disconnected
     */

    public abstract void showDisconnectScreen();

    /**
     * Method that says to the player his id and the list of WindowPatternCard
     * @param user name of player
     * @param id id of player
     * @param windowDeck list of windowPatternCard that sends controller to View
     */

    @Override
    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);


}
