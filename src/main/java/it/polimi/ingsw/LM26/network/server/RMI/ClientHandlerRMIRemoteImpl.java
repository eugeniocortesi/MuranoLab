package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;

import java.rmi.RemoteException;

public class ClientHandlerRMIRemoteImpl implements ClientHandlerRMIRemoteInt {

    ClientHandlerRMIImpl clientHandlerRMI;

    public ClientHandlerRMIRemoteImpl(ServerImpl server) {
        clientHandlerRMI = new ClientHandlerRMIImpl(server);
    }

    public void connected(String id) throws RemoteException {
        clientHandlerRMI.connected(id);
    }

    public void login(String username) throws RemoteException {
        clientHandlerRMI.login(username);
    }

    public void disconnect(String username) throws RemoteException {
        clientHandlerRMI.disconnect(username);
    }


}
