package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.serverController.Observer;

import java.util.ArrayList;

/**
 * Abstract class ClientView implemented by ClientViewSocket and ClientViewRMI
 * @author Chiara Criscuolo
 */

public abstract class ClientView extends ObservableSimple implements Observer {

    /**
     * Method that ask the connection to Server
     */

    public abstract void connect();

    /**
     * Answer of Server about connection
     */

    public abstract void requestedLogin();

    /**
     * Method that sends to Server the name of player
     * @param name username of the player
     */

    public abstract void login ( String name );

    /**
     * Method that receives the answer from login phase
     * @param l result of login phase (True = logged, False = not logged)
     * @param name username of the player
     */

    public abstract void logged ( Boolean l, String name );

    /**
     * Method that receives as answer from login phase that a game is already started
     */

    public abstract void tooManyUsers();

    /**
     * Method called when the player tries to disconnect
     */

    public abstract void disconnect();

    /**
     * Method that sayes the the client is disconnected from Server
     */

    public abstract void disconnected();

    /**
     * Method that sends to Client the list of WindowPatternCard
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of WindowPatternCard
     */

    public abstract void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    /**
     * Method that sends to Server the chose WindowPatternCard from Client
     * @param actionEventWindow information about the chosen WindowPatternCard
     */

    public abstract void chosenWindowPattern(ActionEventWindow actionEventWindow);

    /**
     * Method that sends to the Client the PrivateCard
      * @param privateCard chosen privateCard from Server
     */

    public abstract void sendPrivateCard(ObjectivePrivateCard privateCard);

    /**
     * Method that sends from Client to Server the action made by the player
     * @param actionEvent action made by the player
     */

    public abstract void sendActionEventFromView(ActionEvent actionEvent);

    /**
     *  Method that sends from Server to Client if the action made is right or not
     * @param answer String of answer by Controller
     */

    public abstract void sendAnswerFromController(String answer);

    /**
     * Method that sends the playerZone of each player
     * @param name username of the player
     * @param playerZone Information about the player (his turn or not, id..)
     */

    public abstract void sendBeginTurn(String name, PlayerZone playerZone);

    /**
     * Method that notifies each player that a new player joins the game
     * @param field1 username of the player
     */

    public abstract void sendAddedPlayer(String field1);

    /**
     * Method that notify View
     * @param name username of the player
     */

    public abstract void sendCurrentMenu(String name);

    /**
     * Method that checks if the connection is alive
     */

    public abstract void pong();

    /**
     * Method that sends the score to the View
     */

    public abstract void endGame(String name, int score, String winner, int scoreWinner);
}
