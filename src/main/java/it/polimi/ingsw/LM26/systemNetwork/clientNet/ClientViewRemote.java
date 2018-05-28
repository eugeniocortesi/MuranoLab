package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientViewRemote extends Remote {
    void connect() throws RemoteException;

    void requestedLogin () throws RemoteException;

    void logged (Boolean l, String name) throws RemoteException;

    void tooManyUsers() throws RemoteException;
}
