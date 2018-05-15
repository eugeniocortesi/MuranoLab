package it.polimi.ingsw.LM26.networkServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Loggable extends Remote{

    boolean login( String n ) throws RemoteException;
    void logout( String n ) throws RemoteException;
}
