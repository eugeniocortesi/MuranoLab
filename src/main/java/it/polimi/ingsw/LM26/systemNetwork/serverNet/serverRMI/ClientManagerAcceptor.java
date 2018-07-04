package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;

import java.rmi.RemoteException;

/**
 * ClientManagerAcceptor class
 * @author Chiara Criscuolo
 * It is the implementation of ClientAcceptorRemote
 */

public class ClientManagerAcceptor implements ClientAcceptorRemote {

    private RMIAcceptor acceptor;

    /**
     * Constructor
     * @param rmiAcceptor rmi connections acceptor
     */

    public ClientManagerAcceptor(RMIAcceptor rmiAcceptor){
        acceptor = rmiAcceptor;
    }

    /**
     * It calls method create in acceptor and return the stub of the connection
     * @param clientViewRemote instance if skeleton
     * @return the real stub of the connection
     * @throws RemoteException
     */

    @Override
    public ClientManagerRemote connect(ClientViewRemote clientViewRemote) throws RemoteException {

        return acceptor.create(clientViewRemote);
    }
}
