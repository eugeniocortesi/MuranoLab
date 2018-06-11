package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;


import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
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
import java.util.concurrent.Executors;
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

            Thread t = new Thread(new Runnable(){

                public void run() {
                    try {
                        skeleton.requestedLogin();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            });
            t.start();

            //skeleton.requestedLogin();
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

    }

    @Override
    public void login(String name) {
        LOGGER.log(Level.SEVERE,"User tries to connect with username : " + name);
        if (myserver.checkNumberUsers()){
            boolean result = myserver.addView(name, this);
            if(result){
                this.user = name;
                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, true);
                myserver.getQueueController().pushMessage(actionEventPlayer);

                myserver.checkPlayers();
            }
            LOGGER.log(Level.INFO,"The add result value: " + result);
            Thread t = new Thread(new MyRunnableLogged(user, result));
            t.start();

        }
        else {
            Thread t = new Thread(new Runnable(){

                public void run() {
                    try {
                        skeleton.tooManyUsers();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            });
            t.start();

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
        Thread t = new Thread(new MyRunnableWindow(user, id, (ArrayList<WindowPatternCard>) windowDeck.clone()));
        t.start();
    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);
        myserver.getQueueController().pushMessage(actionEventWindow);

        //this.getObserver().accept(actionEventWindow);
    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard card) {

        Thread t = new Thread(new MyRunnablePrivateCard(card));
        t.start();

    }

    @Override
    public void sendModel(Model m) {

        Thread t = new Thread(new MyRunnableModel(m));
        t.start();

    }

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {
        LOGGER.log(Level.SEVERE,"I have received one actionEvent from " +user);
        myserver.getQueueController().pushMessage(actionEvent);
    }

    @Override
    public void sendAnswerFromController(String answer) {

        Thread t = new Thread(new MyRunnableAnswer(answer));
        t.start();

    }

    @Override
    public void sendBeginTurnMessage(String name, PlayerZone playerZone) {
        Thread t = new Thread(new MyRunnableBeginTurnMessage(name, playerZone));
        t.start();
    }

    @Override
    public void update(Model m) {
        sendModel(m);
    }



    public class MyRunnableWindow implements Runnable{

        volatile String user;
        volatile int id;
        volatile ArrayList<WindowPatternCard> windowDeck;

        public MyRunnableWindow(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
            this.user = user;
            this.id = id;
            this.windowDeck = windowDeck;
        }

        @Override
        public void run() {
            try {
                LOGGER.log(Level.SEVERE,"Asking window card");
                LOGGER.log(Level.SEVERE, windowDeck.toString());
                skeleton.choseWindowPattern(user, id, windowDeck);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyRunnableLogged implements Runnable{

        volatile String user;
        volatile boolean result;

        private MyRunnableLogged(String user, boolean result) {
            this.user = user;
            this.result = result;
        }

        @Override
        public void run() {

            try {
                skeleton.logged(result, user);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyRunnablePrivateCard implements Runnable {

        volatile ObjectivePrivateCard card;

        private MyRunnablePrivateCard(ObjectivePrivateCard privateCard) {

            card= privateCard;
        }

        @Override
        public void run(){

            try {
                LOGGER.log(Level.SEVERE,"Sending private card");
                skeleton.sendPrivateCard(card);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyRunnableModel implements Runnable {

        volatile Model m;

        private MyRunnableModel(Model m) {

            this.m = m;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending model");
                skeleton.sendModel(m);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyRunnableAnswer implements Runnable {

        volatile String answer;

        private MyRunnableAnswer(String answer) {
            this.answer = answer;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending actionEvent");
                skeleton.sendAnswerFromController(answer);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyRunnableBeginTurnMessage implements Runnable {

        volatile String name;
        volatile PlayerZone playerZone;
        private MyRunnableBeginTurnMessage(String name, PlayerZone playerZone) {

            this.name = name;
            this.playerZone = playerZone;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending player zone");
                skeleton.sendBeginTurnMessage(name, playerZone);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}


