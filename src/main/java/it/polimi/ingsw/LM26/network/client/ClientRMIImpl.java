package it.polimi.ingsw.LM26.network.client;

import it.polimi.ingsw.LM26.network.server.RMI.ClientHandlerRMIRemoteInt;
import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.networkServer.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMIImpl {

    private  static int PORT ;
    private DataClientImplementation dataClientImplementation;
    private BufferedReader inKeyboard;
    ViewInt concreteView;
    private ConnectionFromServer connectionFromServer;
    private ConnectionToServer connectionToServer;

    public ClientRMIImpl(ViewInt concreteView) {

        try {

            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            this.dataClientImplementation = new DataClientImplementation();
            DataClientConfiguration dataClientConfiguration = this.dataClientImplementation.implementation();
            this.concreteView = concreteView;
            this.connectionFromServer = new ConnectionFromServer(concreteView);

            PORT = dataClientConfiguration.getClientRMIPORT();
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            //Looking up the registry for the remote object
            ClientHandlerRMIRemoteInt stub = (ClientHandlerRMIRemoteInt) registry.lookup("ClientHandlerRMI");
            // Calling the remote method using the obtained object
            //System.out.println("Insert login username: ");

            this.connectionToServer = new ConnectionToServer(stub);


            concreteView.showLoginScreen();
            String name = inKeyboard.readLine();
            stub.login(name);
            System.out.println("Remote method invoked ");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
