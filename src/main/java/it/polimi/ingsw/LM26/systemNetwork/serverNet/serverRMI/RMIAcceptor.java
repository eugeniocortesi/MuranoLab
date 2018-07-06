package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;
import it.polimi.ingsw.LM26.fileConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMIAcceptor class
 * @author Chiara Criscuolo
 * It manages the new RMI connection
 */

public class RMIAcceptor {

    private ServerBase myserver;

    private int RMIPORTServer;

    private ClientAcceptorRemote stub;


    /**
     * Constructor
     * @param serverBase reference to the Server
     * @param data dataServerConfiguration
     */

    public RMIAcceptor(ServerBase serverBase, DataServerConfiguration data){

        myserver = serverBase;

        this.RMIPORTServer = data.getServerRMIPORT();

    }

    /**
     * Method that creates the stub that accepts all RMI connections
     */

    public void bind() {

        try {

            ClientAcceptorRemote clientAcceptorRemote = new ClientManagerAcceptor(this);

            stub = (ClientAcceptorRemote) UnicastRemoteObject.exportObject(clientAcceptorRemote, RMIPORTServer);

            System.out.println("RMI SERVER PORT: " + RMIPORTServer);

            Registry registry = LocateRegistry.createRegistry(RMIPORTServer);

            registry.bind("ClientManagerRemote", stub);

        } catch (RemoteException | AlreadyBoundException e) {

            System.err.println("Impossible to create stub, reset server and try again");

        }
    }

    /**
     * Method that creates a stub and a clientManagerRMI for each client
     * returns the stub created to the client
     * Add the clientViewRemote to the clientManagerRMI created
     * @param clientViewRemote skeleton of the client
     * @return clientManagerRemote = stub
     */


    public ClientManagerRemote create(ClientViewRemote clientViewRemote) {

        ClientManagerRMI clientManagerRMI = new ClientManagerRMI(myserver);

        clientManagerRMI.setSkeleton(clientViewRemote);

        ClientManagerRemote clientManagerRemote = null;
        try {
            clientManagerRemote = new ClientManagerRMIRemote(clientManagerRMI);

        } catch (RemoteException e) {

            System.err.println("Unable to create the stub, reset server and try again");
        }

        return clientManagerRemote;
    }

}
