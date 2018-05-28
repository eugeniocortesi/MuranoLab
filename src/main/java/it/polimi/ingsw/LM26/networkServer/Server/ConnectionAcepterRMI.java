package it.polimi.ingsw.LM26.networkServer.Server;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.*;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.util.Vector;

import static java.rmi.registry.LocateRegistry.createRegistry;

public class ConnectionAcepterRMI implements Remote {

    private int RMIPORT;
    //private ClientHandlerRMI clientHandlerRMI;
    private Server server;
    private Registry r;

    public ConnectionAcepterRMI(Server server, DataServerConfiguration dataServerConfiguration){

        this.server = server;
        RMIPORT = dataServerConfiguration.getServerRMIPORT();
        //TODO clientHandler RMI
        //clientHandlerRMI = new ClientRMI();

        try {
            //System.setSecurityManager(new RMISecurityManager());

            r = createRegistry(RMIPORT);
            //r.bind("server", clientHandlerRMI);


            //ChatServerInt b=new ChatServer();
            //Naming.rebind("rmi://127.0.0.1/myabc", b);
            System.out.println("[System] RMI Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }

}
