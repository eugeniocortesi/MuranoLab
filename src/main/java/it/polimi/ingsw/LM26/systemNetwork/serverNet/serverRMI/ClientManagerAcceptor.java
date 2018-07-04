package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.PrivateCardMessage;

import java.rmi.RemoteException;

public class ClientManagerAcceptor implements ClientAcceptorRemote {

    private RMIAcceptor acceptor;

    public ClientManagerAcceptor(RMIAcceptor rmiAcceptor){
        acceptor = rmiAcceptor;
    }

    @Override
    public ClientManagerRemote connect(ClientViewRemote clientViewRemote) throws RemoteException {

        return acceptor.create(clientViewRemote);
    }
}
