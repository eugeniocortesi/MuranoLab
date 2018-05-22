package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ConnectionAcceptorRMIImpl {

    private int RMIPORT;
    ServerImpl server;

    public ConnectionAcceptorRMIImpl(ServerImpl server, DataServerConfiguration dataServerConfiguration){

        this.server= server;
        this.RMIPORT = dataServerConfiguration.getRMIPORT();

        try{
            ClientHandlerRMIInt clientHandlerRMIRemote = new ClientHandlerRMIRemoteImpl(server);
            ClientHandlerRMIInt stub = (ClientHandlerRMIInt) UnicastRemoteObject.exportObject(clientHandlerRMIRemote, RMIPORT);
            Registry registry = LocateRegistry.createRegistry(RMIPORT);
            registry.bind("ClientHandlerRMI",  stub);
            System.err.println("Server ready");

        }catch (Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
