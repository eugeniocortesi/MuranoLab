package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.network.server.ServerImpl;

import java.rmi.RemoteException;

public class ClientHandlerRMIRemoteImpl implements ClientHandlerRMIInt {

    ClientHandlerRMIImpl clientHandlerRMI;

    public ClientHandlerRMIRemoteImpl(ServerImpl server) {
        clientHandlerRMI = new ClientHandlerRMIImpl(server);
    }

    public void login(String username) throws RemoteException {
        clientHandlerRMI.login(username);
    }

    public void disconnect(String username) throws RemoteException {
        clientHandlerRMI.disconnect(username);
    }
}
