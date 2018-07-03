package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ClientViewRemote interface
 * @author Chiara Criscuolo
 * Interface that is implemented as skeleton
 */

public interface ClientViewRemote extends Remote {

    /**
     * Method called to ak the connection to the Server
     * @throws RemoteException
     */

    void connect() throws RemoteException;

    /**
     * Method called to aks login to the Server
     * @throws RemoteException
     */

    void requestedLogin () throws RemoteException;

    /**
     * Method that returns the loginn result
     * @param l boolean that represents the result of login phase
     * @param name username of the player
     * @throws RemoteException
     */

    void logged (Boolean l, String name) throws RemoteException;

    /**
     * Method that says that a game is already going on
     * @throws RemoteException
     */

    void tooManyUsers() throws RemoteException;

    /**
     * Method that sends the list of windowPatternCard
     * @param user username of the player
     * @param id ìd number of the player
     * @param windowDeck list of windowPatternCard
     * @throws RemoteException
     */

    void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) throws RemoteException;

    /**
     * Method that receives Model message from Server
     * @param m Model received from Server
     * @throws RemoteException
     */

    void sendModel(Model m) throws RemoteException;

    /**
     * Method that receives a string from Controller
     * @param answer string from Controller
     * @throws RemoteException
     */

    void sendAnswerFromController(String answer) throws RemoteException;

    /**
     * Method that receives the privateCard from Controller
     * @param privateCard private card assigned to the player
     * @throws RemoteException
     */

    void sendPrivateCard(ObjectivePrivateCard privateCard) throws RemoteException;

    /**
     * Method that receives information about his turn
     * @param name username of the player
     * @param playerZone PlayerZone of the player
     * @throws RemoteException
     */

    void sendBeginTurnMessage(String name, PlayerZone playerZone) throws RemoteException;

    /**
     * Method called by Server to avvert that the connection is not more available
     * @throws RemoteException
     */

    void disconnected() throws RemoteException;

    /**
     * Method that notify that another player joins the game
     * @param name username of the added player
     * @throws RemoteException
     */

    void sendAddedPlayer(String name) throws RemoteException;

    /**
     * Method that calls showCurrentMenu in View
     * @param name username of the player
     * @throws RemoteException
     */

    void sendCurrentMenu(String name) throws RemoteException;

    /**
     * Method called to check if the connection is alive
     * @throws RemoteException
     */

    void pong() throws RemoteException;
}
