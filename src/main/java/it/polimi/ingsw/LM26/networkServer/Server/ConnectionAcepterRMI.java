package it.polimi.ingsw.LM26.networkServer.Server;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientInt;

import java.util.Vector;

public class ConnectionAcepterRMI implements ConnectionAcepter {

    private Vector<ClientInt> connections;
    private Server server;

    public ConnectionAcepterRMI(Server server){

        this.server = server;
        connections = new Vector<ClientInt>();

        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            //ChatServerInt b=new ChatServer();
            //Naming.rebind("rmi://127.0.0.1/myabc", b);
            System.out.println("[System] RMI Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }

    }

    public void acceptConnection() {

    }
}
