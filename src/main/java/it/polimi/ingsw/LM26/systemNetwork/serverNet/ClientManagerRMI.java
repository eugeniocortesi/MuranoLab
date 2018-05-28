package it.polimi.ingsw.LM26.systemNetwork.serverNet;


import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientViewRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ClientManagerRMI implements ClientManager {

    private ServerBase myserver;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private ClientViewRemote skeleton;

    public ClientManagerRMI(ServerBase serverBase, int RMIPORTServer, int RMIPORTClient, String address){

        myserver = serverBase;
        this.RMIPORTServer = RMIPORTServer;
        this.RMIPORTClient = RMIPORTClient;
        this.address = address;
    }

    public void connect(){

        //Take Skeleton
        try {
            // Getting the registry
           // Registry registry = LocateRegistry.getRegistry(address, RMIPORTClient);
            Registry registry = LocateRegistry.getRegistry(address, RMIPORTServer);
            //Looking up the registry for the remote object
            skeleton = (ClientViewRemote) registry.lookup("ClientViewRemote"+getAvailableId());
            System.out.println("Took Skeleton");
            myserver.addClientManager(this);
            skeleton.requestedLogin();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public int getAvailableId(){
        return myserver.lobbySize();
    }

    @Override
    public void requestedLogin() {
        System.out.println("The client RMI is connected. He tries to login");
        /*try {
            skeleton.login(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void login(String name) {
        System.out.println("User tries to connect with username : " + name);
        if (myserver.checkNumberUsers()){
            boolean result = myserver.addView(name, this);
            System.out.println("The add reult value: " + result);
            try {
                skeleton.logged(result, name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                skeleton.tooManyUsers();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }




    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void disconnect() {

    }
}
