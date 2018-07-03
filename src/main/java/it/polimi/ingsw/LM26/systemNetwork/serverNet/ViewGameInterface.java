package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.serverController.Observable;
import java.util.ArrayList;

/**
 * ViewGameInterface abstract class
 * Connects Controller to Server/View
 * Only game methods
 */

public abstract class ViewGameInterface extends Observable {

    /**
     * Method called to send list of WindowPatternCard
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of windowPatternCard
     */

    public abstract void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    /**
     * Method called start acceptor in Server
     * @param observer Controller
     * @param model Model
     */

    public abstract void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model);

    /**
     * Method called to send PrivateCard to the View
     * @param name username of the player
     * @param privateCard privateCard chosen for the player
     */

    public abstract void showPrivateCard(String name, ObjectivePrivateCard privateCard);

    /**
     * Method called to send PlayerZone
     * @param name username of the player
     * @param player playerZone of the player
     */

    public abstract void showSetPlayerMenu(String name, PlayerZone player);

    /**
     * Method that calls to the View the currentMenu
     * @param name username of the player
     */

    public abstract void showCurrentMenu(String name);

    /**
     * Method that sends the answer from Controller to the View
     * @param name username of the player
     * @param answer string of answer from Controller
     */

    public abstract void showAnswerFromController(String name,String answer);

    /**
     * Method that sends to the View final score of the game
     * @param name username of the player
     * @param score final score of the game
     */

    public abstract void showEndGame(String name, Object score);

}
