package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.network.server.ClientHandlerInt;
import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualView;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientHandlerRMIImpl extends ClientHandlerInt {

    private ServerImpl myserver;
    private  static int PORT ;

    public ClientHandlerRMIImpl(ServerImpl server){
        this.myserver = server;
    }

    public void connected(String id) {

        DataClientImplementation dataClientImplementation = new DataClientImplementation();
        DataClientConfiguration dataClientConfiguration = dataClientImplementation.implementation();

        System.out.println(" Client RMI connected ");

        PORT = dataClientConfiguration.getClientRMIPORT();
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            //Looking up the registry for the remote object
            VirtualView skeleton = (VirtualView) registry.lookup(id);
            myserver.addView(skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }


    public void login(String username) {

        if(this.myserver.checkLogin(username) && this.myserver.checkNumberUser()){
            System.out.println(username + " logged");
            myserver.addUsername(username, this);
            System.out.println ("I'm adding " + myserver.getUserConnections().size() + " element");
            //TODO notify other players


        }
        else{
            System.out.println(username + " not logged");
        }
    }

    public void disconnect(String username) {

    }

}
