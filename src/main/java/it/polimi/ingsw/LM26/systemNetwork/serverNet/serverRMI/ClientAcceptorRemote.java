package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientAcceptorRemote extends Remote {

    public ClientManagerRemote connect(ClientViewRemote clientViewRemote) throws RemoteException;
}
