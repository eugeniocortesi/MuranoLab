package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;
import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * RMIAcceptor class
 * @author Chiara Criscuolo
 * It manages the new RMI connection
 */

public class RMIAcceptor {

    private ServerBase myserver;

    private int RMIPORTServer;

    private int RMIPORTClient;

    private String address;

    private ClientAcceptorRemote stub;

    private static final Logger LOGGER = Logger.getLogger(RMIAcceptor.class.getName());

    /**
     * Constructor
     * @param serverBase reference to the Server
     * @param data dataServerConfiguration
     */

    public RMIAcceptor(ServerBase serverBase, DataServerConfiguration data){

        myserver = serverBase;

        this.RMIPORTClient = data.getClientRMIPORT();

        this.RMIPORTServer = data.getServerRMIPORT();

        this.address = data.getIp();

    }

    /**
     * Method that creates the stub
     */

    public void bind(){

        try{


            ClientAcceptorRemote clientAcceptorRemote = new ClientManagerAcceptor(this);

            stub = (ClientAcceptorRemote) UnicastRemoteObject.exportObject(clientAcceptorRemote, RMIPORTServer);

            Registry registry = LocateRegistry.createRegistry(RMIPORTServer);

            registry.bind("ClientManagerRemote", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        /*

        try{
            ClientManager clientManagerRMI = new ClientManagerRMI(myserver, this.RMIPORTServer, this.RMIPORTClient, address);

            Thread t = new Thread(clientManagerRMI);



            ClientManagerRemote clientManagerRemote = new ClientManagerRMIRemote(clientManagerRMI);

            stub = (ClientManagerRemote) UnicastRemoteObject.exportObject(clientManagerRemote, RMIPORTServer);

            Registry registry = LocateRegistry.createRegistry(RMIPORTServer);

            registry.bind("ClientManagerRemote",  stub);

            LOGGER.log(Level.WARNING,"Server ready, stub created");

            t.start();

        }catch (Exception e){

            System.err.println("Server enable to create the stub, reset and try");
        }


       /* try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(address, RMIPORTClient);
            //Looking up the registry for the remote object
            ClientViewRemote skeleton = (ClientViewRemote) registry.lookup("ClientViewRemote");
            myserver.addView(skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }*/
    }

    public ClientManagerRemote create(ClientViewRemote clientViewRemote) {

        ClientManagerRMI clientManagerRMI = new ClientManagerRMI(myserver, RMIPORTServer, RMIPORTClient, address);

        clientManagerRMI.setSkeleton(clientViewRemote);

        ClientManagerRemote clientManagerRemote = null;
        try {
            clientManagerRemote = new ClientManagerRMIRemote(clientManagerRMI);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return clientManagerRemote;
    }

}
