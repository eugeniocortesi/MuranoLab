package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import java.rmi.RemoteException;

public class ClientViewRMIRemote implements ClientViewRemote {

    ClientViewRMI clientViewRMI;

    public ClientViewRMIRemote(ClientViewRMI  clientViewRMI){
        this.clientViewRMI = clientViewRMI;
    }

    @Override
    public void connect() throws RemoteException {
        clientViewRMI.connect();
    }

    @Override
    public void requestedLogin() throws RemoteException {
        clientViewRMI.requestedLogin();
    }

    @Override
    public void logged(Boolean l, String name) throws RemoteException {
        clientViewRMI.logged(l,name);
    }

    @Override
    public void tooManyUsers() throws RemoteException {
        clientViewRMI.tooManyUsers();
    }


}
