package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface ClientManagerRemote
 * @author Chiara Criscuolo
 * It is the Stub called by client to be connected Server
 */
public interface ClientManagerRemote extends Remote {

    /**
     * Method called to be connected to the Server
     * @throws RemoteException
     */
    void connect() throws  RemoteException;

    /**
     * Method called to return the Client ID
     * @return id integer that identify the client
     * @throws RemoteException
     */

    int getAvailableId() throws RemoteException;

    /**
     * Method called to login
     * @param name name of the player
     * @throws RemoteException
     */

    void login(String name) throws RemoteException;

    /**
     * Method called to exit from the Game
     * @param s name of the player
     * @throws RemoteException
     */

    void disconnect(String s) throws RemoteException;

    /**
     * Method to send the chosen WindowPatternCard
     * @param actionEventWindow information about the answer (WindowPatternCard)
     * @throws RemoteException
     */

    void chosenWindowPattern(ActionEventWindow actionEventWindow) throws RemoteException;

    /**
     * Method that sends from the Client to the Server an ActionEvent
     * @param actionEvent chosen action
     * @throws RemoteException
     */

    void sendActionEventFromView(ActionEvent actionEvent) throws RemoteException;

    /**
     * Methos that controls if the connection is alive
     * @throws RemoteException
     */
    void pong() throws RemoteException;
}
