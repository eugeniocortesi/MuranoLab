package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;

import java.util.ArrayList;

/**
 * ClientManager abstract class
 * @author Chiara Criscuolo
 * Class that manages one connection for each client
 */
public abstract class ClientManager implements Runnable, ObserverSimple {

    /**
     * Method that manages the initial connection
     */

    public abstract void connect();

    /**
     * Method that get the id number for the connection
     * @return id number for the connection
     */

    public abstract int getAvailableId();

    /**
     * Method that manages the request of login
     */

    public abstract void requestedLogin();

    /**
     * Method that manages the login phase
     * @param name username of player
     */

    public abstract void login(String name);

    /**
     * Method that manages the result of login phase
     * @param l result of login (true = logged, false = not logged)
     * @param name username of player
     */

    public abstract void logged(Boolean l, String name);

    /**
     * Method that notify to the client that us disconnected
     */

    public abstract void disconnected();

    /**
     * Method that notify that a player is trying to disconnect
     * @param s
     */

    public abstract void disconnect(String s );

    /**
     * Method that reimplements run
     */

    public abstract void run();

    /**
     * Method that sends an list of WindowPatternCard
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of WindowPatternCard
     */

    public abstract void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    /**
     * Method that receives an actionEventWindow
     * @param actionEventWindow ActionEventWindow thant contains the WindowPatternCard chosen
     */

    public abstract void chosenWindowPattern(ActionEventWindow actionEventWindow);

    /**
     * Method that sends the PrivateCard
     * @param card PrivateCard
     */

    public abstract void sendPrivateCard(ObjectivePrivateCard card);

    /**
     * Method that sends m
     * @param m new Model
     */

    public abstract void sendModel(Model m);

    /**
     * Method that receive an actionEvent
     * @param actionEvent ActionEvent : chosen move of the player
     */

    public abstract void sendActionEventFromView(ActionEvent actionEvent);

    /**
     * Method that sends answer to View
     * @param message String from Controller
     */

    public abstract void sendAnswerFromController(String message);

    /**
     * Method that sends playerZone for each player
     * @param name username of the player
     * @param playerZone PlayerZone : all information about his Zone
     */

    public abstract void sendBeginTurnMessage(String name, PlayerZone playerZone);

    /**
     * Method that notify that a new player joins the Game
     * @param name username of the added player
     */

    public abstract void sendAddedPlayer(String name);

    /**
     * Method that notify Client of Current Menu
     * @param name username of the player
     */

    public abstract void sendCurrentMenu(String name);

    /**
     * Method that sends the score
     * @param username username of the player
     * @param score points of the player
     * @param winner username of the winner
     * @param scoreWinner points of the winner
     */

    public abstract void sendEndGame(String username, int score, String winner, int scoreWinner);

    /*
    Method from ClientManager to Client View that checks if the connection il alive
     */

    public abstract void ping();

    /**
     * Method from ClientView to ClientManager that sends the pong
     */

    public abstract void pong();

    /**
     * Method that interrupt the connection from ClientManager to ClientView
     */

    public abstract void stop();

    /**
     * Method that returns the name of the connection
     * @return name of player in the connection
     */

    public abstract String getName();
}
