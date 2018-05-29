package it.polimi.ingsw.LM26.network.client;

import it.polimi.ingsw.LM26.network.server.RMI.ClientHandlerRMIRemoteInt;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMIImpl {

    private  static int PORT ;
    private DataClientImplementation dataClientImplementation;
    private ClientHandlerRMIRemoteInt stub;
    ViewInt concreteView;
    private ConnectionFromServer connectionFromServer;
    private ConnectionToServer connectionToServer;

    public ClientRMIImpl(ViewInt concreteView) {

        try {
            this.dataClientImplementation = new DataClientImplementation();
            DataClientConfiguration dataClientConfiguration = this.dataClientImplementation.implementation();

            PORT = dataClientConfiguration.getClientRMIPORT();
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            //Looking up the registry for the remote object
            stub = (ClientHandlerRMIRemoteInt) registry.lookup("ClientHandlerRMI");
            // Calling the remote method using the obtained object
            //System.out.println("Insert login username: ");

            this.connectionToServer = new ConnectionToServer(stub);
            this.connectionFromServer = new ConnectionFromServer(concreteView, registry, 1100);

            this.concreteView = concreteView;

            System.out.println("Remote method invoked ");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void login(String username){
            connectionToServer.login(username);
    }

    public void connected(VirtualViewInt virtualViewInt){

        connectionToServer.connected(virtualViewInt);

    }
}
