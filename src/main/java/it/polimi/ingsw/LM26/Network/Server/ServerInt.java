package it.polimi.ingsw.LM26.Network.Server;

import it.polimi.ingsw.LM26.Network.Loggable;

import java.io.Serializable;
import java.rmi.*;

public interface ServerInt extends Loggable {

    public void accept();

    public boolean login ( String n ) throws RemoteException;

    public void logout( String n ) throws RemoteException;

    public void send(Serializable object);

    public Serializable receive();

}
