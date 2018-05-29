
package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManagerRemote;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientViewRMI implements ClientView {

    private ConsoleStrings concreteClientView;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private int id;
    private ClientManagerRemote stub;

    public ClientViewRMI(ConsoleStrings concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;
        RMIPORTServer = data.getServerRMIPORT();
        RMIPORTClient =data.getClientRMIPORT();
        address = data.getIp();
        id = 0;
        getStub();
    }

    public void connect(){
        bind();
    }


    public void bind(){
        //Creates skeleton

        try{
            ClientViewRMIRemote clientViewRMIRemote = new ClientViewRMIRemote(this);
            ClientViewRemote skeleton = (ClientViewRemote) UnicastRemoteObject.exportObject(clientViewRMIRemote, RMIPORTClient);
            //Registry registry = LocateRegistry.createRegistry(RMIPORTClient);
            Registry registry = LocateRegistry.getRegistry(address, RMIPORTServer );
            registry.bind("ClientViewRemote"+id,  skeleton);
            System.err.println("Client ready, created skeleton");
            stub.connect();

        }catch (Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    //TODO start connect before bind, return int and put it inside the name used in bind
    public void getStub(){
        //takes the stub

        // Getting the registry
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(address, RMIPORTServer);
            //Looking up the registry for the remote object
            stub = (ClientManagerRemote) registry.lookup("ClientManagerRemote");
            System.out.println("Took the stub");
            id = stub.getAvailableId();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void requestedLogin() {
        System.out.println("Now you are connected in RMI to Server");
        concreteClientView.showLoginScreen();

    }

    @Override
    public void login(String name) {
        try {
            stub.login(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logged(Boolean l, String name) {
        if (l== true)
            concreteClientView.showLoggedScreen();
        else
            concreteClientView.showAlreadyLoggedScreen();
    }

    @Override
    public void tooManyUsers() {
        concreteClientView.showTooManyUsersScreen();
    }

    @Override
    public void disconnect() {
        concreteClientView.showDisconnectScreen();
    }
}