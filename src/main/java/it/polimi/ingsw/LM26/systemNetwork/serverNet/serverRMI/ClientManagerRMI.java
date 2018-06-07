package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;


import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI.ClientViewRemote;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManagerRMI extends ClientManager {

    private ServerBase myserver;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private ClientViewRemote skeleton;
    private String user;
    private static final Logger LOGGER = Logger.getLogger(ClientManagerRMI.class.getName());

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
            String addr= RemoteServer.getClientHost();
            Registry registry = LocateRegistry.getRegistry(addr, RMIPORTServer);
            //Looking up the registry for the remote object
            skeleton = (ClientViewRemote) registry.lookup("ClientViewRemote"+getAvailableId());
            LOGGER.log(Level.WARNING, "Took Skeleton");
            myserver.addClientManager(this);

            skeleton.requestedLogin();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    public int getAvailableId(){
        return myserver.lobbySize();
    }

    @Override
    public void requestedLogin() {
        LOGGER.log(Level.SEVERE,"The client RMI is connected. He tries to login");
        /*try {
            skeleton.login(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void login(String name) {
        LOGGER.log(Level.SEVERE,"User tries to connect with username : " + name);
        if (myserver.checkNumberUsers()){
            boolean result = myserver.addView(name, this);
            if(result){
                this.user = name;

                myserver.checkPlayers();
            }
            LOGGER.log(Level.INFO,"The add result value: " + result);
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

    @Override
    public void run() {

    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        try {
            LOGGER.log(Level.SEVERE,"Asking window card");
            skeleton.choseWindowPattern(user, id, windowDeck);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);
        myserver.getQueueController().pushMessage(actionEventWindow);

        //this.getObserver().accept(actionEventWindow);
    }
}
