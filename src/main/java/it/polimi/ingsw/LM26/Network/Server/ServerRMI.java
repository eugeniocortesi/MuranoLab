package it.polimi.ingsw.LM26.Network.Server;
import java.io.Serializable;
import java.rmi.*;

public class ServerRMI implements ServerInt {



    public void accept() {
        ;
    }

    public boolean login(String n) throws RemoteException {
        return false;
    }

    public void logout(String n) throws RemoteException {
        ;
    }

    public void send(Serializable object) {
        ;
    }

    public Serializable receive() {
        return null;
    }
}