package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualView;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandlerRMIRemoteInt extends Remote {

    void login (String username) throws RemoteException;

    void disconnect(String username) throws RemoteException;

    void connected(String id) throws RemoteException;
}
