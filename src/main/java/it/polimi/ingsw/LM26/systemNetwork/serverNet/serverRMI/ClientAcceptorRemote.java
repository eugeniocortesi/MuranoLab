package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientAcceptorRemote interface
 * @author Chiara Criscuolo
 * It is the stubAcceptor, accpets RMI connections
 */
public interface ClientAcceptorRemote extends Remote {

    /**
     * Method called by the client trying to connect
     * @param clientViewRemote instance if skeleton
     * @return instance of stub
     * @throws RemoteException
     */

    ClientManagerRemote connect(ClientViewRemote clientViewRemote) throws RemoteException;
}
