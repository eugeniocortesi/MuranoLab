package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientManagerRemote extends Remote {

    void connect() throws  RemoteException;

    int getAvailableId() throws RemoteException;

    void login(String name) throws RemoteException;

    void disconnect() throws RemoteException;
}
