package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * ClientManagerRMIRemote class
 * @author Chiara Criscuolo
 * It implement ClientManagerRemote
 * It is the stub called by the RMI client
 * When a call arrives it calls the corresponding method in CLientManagerRMI
 */

public class ClientManagerRMIRemote extends UnicastRemoteObject implements ClientManagerRemote {

    private ClientManager clientManagerRMI;

    /**
     * Constructor
     * @param clientManager reference of clientManagerRMI
     * @throws RemoteException
     */
    public ClientManagerRMIRemote(ClientManager clientManager) throws RemoteException{
        clientManagerRMI = clientManager;
    }

    /**
     * Method called to be connected to the Stub
     * @throws RemoteException
     */

    @Override
    public void connect() throws RemoteException {
        clientManagerRMI.connect();
    }

    /**
     * Method that get the available id of the connection
     * @return id number of the connection
     * @throws RemoteException
     */

    @Override
    public int getAvailableId() throws RemoteException {
        return clientManagerRMI.getAvailableId();
    }

    /**
     * Method that begins the login phase
     * @param name name of the player
     * @throws RemoteException
     */

    @Override
    public void login(String name) throws RemoteException {
        clientManagerRMI.login(name);
    }

    /**
     * Method that begin the disconnect phase
     * @param s name of the player
     * @throws RemoteException
     */

    @Override
    public void disconnect(String s) throws RemoteException {
        clientManagerRMI.disconnect(s);
    }

    /**
     * Method that receives the actionEventWindow
     * @param actionEventWindow information about the answer (WindowPatternCard)
     * @throws RemoteException
     */

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) throws RemoteException {
        clientManagerRMI.chosenWindowPattern(actionEventWindow);
    }

    /**
     * Method that receives an actionEvent
     * @param actionEvent chosen action
     * @throws RemoteException
     */

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) throws RemoteException {
        clientManagerRMI.sendActionEventFromView(actionEvent);
    }

    /**
     * Method that checks if the connection is alive
     * @throws RemoteException
     */

    @Override
    public void pong() throws RemoteException {
        clientManagerRMI.pong();
    }


}
