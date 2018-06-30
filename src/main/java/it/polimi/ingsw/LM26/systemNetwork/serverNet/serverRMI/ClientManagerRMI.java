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
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.PlayerConnectionMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayers;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionPlayers;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskNetworkPlayers;

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

/**
 * ClientManagerRMI class
 * It manages the RMI connection
 */
public class ClientManagerRMI extends ClientManager {

    private ServerBase myserver;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private ClientViewRemote skeleton;
    private String user;
    TimerPlayers timerPlayers;
    TimerTaskActionPlayers timerTaskActionPlayers;
    TimerTaskNetworkPlayers timerTaskNetworkPlayers;
    private static final Logger LOGGER = Logger.getLogger(ClientManagerRMI.class.getName());

    /**
     * Constructor
     * @param serverBase Server
     * @param RMIPORTServer RMI port of Server
     * @param RMIPORTClient RMI port of Client
     * @param address IP of Server
     */
    public ClientManagerRMI(ServerBase serverBase, int RMIPORTServer, int RMIPORTClient, String address){

        myserver = serverBase;
        this.RMIPORTServer = RMIPORTServer;
        this.RMIPORTClient = RMIPORTClient;
        this.address = address;
        timerPlayers = new TimerPlayers(myserver, myserver.getTimerConfiguration());

    }

    /**
     * method that take the skeleton from Client and add the new client to the lobby
     * Then call method "requestedLogin" in the client
     */
    public void connect(){

        //Take Skeleton
        try {
            // Getting the registry
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

    /**
     * method not used in this implementation of ClientManager
     * @throws UnsupportedOperationException if the method is called
     */

    @Override
    public void requestedLogin() {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     * method called by client that controls if this name is already logged
     * if the name is already playing answer to the client to change the name
     * if there is already a game on going answer to the client to wait
     * otherwise it memorizes the name and wait the beginning of the game
     * @param name name of the player in login phase
     */

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

            if(result){
                //Start Network Timer
                timerTaskNetworkPlayers = timerPlayers.scheduleTimerNetworkPlayer(this);
                LOGGER.log(Level.WARNING, "Timer network Begin");
                Thread t1 = new Thread(new myRunnablePing());
                t1.start();
            }

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

    /**
     * method not used in this implementation of ClientManager
     * @param l true or false if the player is logged or not
     * @param name name of the player
     * @throws UnsupportedOperationException if the method is called
     */

    @Override
    public void logged(Boolean l, String name) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * method that manages the disconnection of the player
     */

    @Override
    public void disconnected() {

        LOGGER.log(Level.SEVERE,user+ " client RMI is disconnected");
        myserver.getClientManagerList().removeClientManager(user);
        Thread t = new Thread(new Runnable(){

            public void run() {
                try {
                    skeleton.disconnected();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        });
        t.start();

    }

    /**
     * it receive a disconnect message and notify the controller of the exit from the game
     * Then calls disconnected();
     * @param s name of the player
     */

    @Override
    public void disconnect(String s) {

        LOGGER.log(Level.SEVERE,"User " + s + " tries to disconnect");
        ActionEventPlayer actionEventPlayer = new ActionEventPlayer(s, false);
        myserver.getQueueController().pushMessage(actionEventPlayer);
        disconnected();
    }

    @Override
    public void run() {

        //TODO wait()??
        //TODO Add JavaDoc
    }

    /**
     * method that sends to the client an ArrayList of WindowPatternCard
     * Starts the timer of an action
     * @param user name of the player
     * @param id id of the player
     * @param windowDeck ArrayList of WindowPatternCard
     */

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        Thread t = new Thread(new MyRunnableWindow(user, id, (ArrayList<WindowPatternCard>) windowDeck.clone()));
        t.start();
        timerTaskActionPlayers = timerPlayers.scheduleTimerActionPlayer(user);
    }

    /**
     * method that receive and actionEventWindow as answer from Client and sends it to the Controller
     * it manages also the timer
     * @param actionEventWindow actionEvent that contains the information about windowCard from Client
     */

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        timerTaskActionPlayers.setArrivedMessage(true);
        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);
        myserver.getQueueController().pushMessage(actionEventWindow);

    }

    /**
     * Method that sends the random PrivateCard to the Client
     * @param card random PrivateCard chosen by Controller
     */
    @Override
    public void sendPrivateCard(ObjectivePrivateCard card) {

        Thread t = new Thread(new MyRunnablePrivateCard(card));
        t.start();

    }

    /**
     * Method that sends the updated Model to the Client
     * @param m updated Model
     */

    @Override
    public void sendModel(Model m) {

        Thread t = new Thread(new MyRunnableModel(m));
        t.start();

    }

    /**
     * Method that receives an actionEvent from the Client and sends it to Controller
     * Updates the timer
     * @param actionEvent answer from Client
     */

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {

        timerTaskActionPlayers.setArrivedMessage(true);
        LOGGER.log(Level.SEVERE,"I have received one actionEvent from " +user);
        myserver.getQueueController().pushMessage(actionEvent);
    }

    /**
     * Method that receive a String from Controller and sends it to the Client
     * @param answer string of answer from Controller after an actionEvent
     */

    @Override
    public void sendAnswerFromController(String answer) {

        Thread t = new Thread(new MyRunnableAnswer(answer));
        t.start();

    }

    /**
     * Method that sends to the Client his playerZone
     * @param name name of the player
     * @param playerZone playerZone of the player
     */

    @Override
    public void sendBeginTurnMessage(String name, PlayerZone playerZone) {

        timerPlayers.resetTimerActionPlayer();
        timerTaskActionPlayers = timerPlayers.scheduleTimerActionPlayer(user);
        Thread t = new Thread(new MyRunnableBeginTurnMessage(name, playerZone));
        t.start();
    }

    /**
     * Method that says to the Client that another player is in the Game
     * @param name name of the new player that is logged
     */

    @Override
    public void sendAddedPlayer(String name) {
        Thread t = new Thread(new MyRunnableAddedPlayer(name));
        t.start();
    }

    /**
     * Method that calls in Client View showCurrentMenu
     * Updates the timer
     * @param name of the player
     */
    @Override
    public void sendCurrentMenu(String name) {
        //TODO CHECK IF IT WILL BE DELETED
        timerPlayers.resetTimerActionPlayer();
        timerTaskActionPlayers = timerPlayers.scheduleTimerActionPlayer(user);
        Thread t = new Thread(new myRunnableCurrentMenu(name));
        t.start();

    }

    /**
     * Method that sends to the player his points
     * @param score points of the player
     */
    @Override
    public void sendEndGame(Object score) {
        //TODO
    }

    /**
     * Method that checks if the player is online or not sending a ping message
     */
    @Override
    public void ping() {

        Thread t = new Thread(new myRunnablePing());
        t.start();
    }

    /**
     * Method that receive the answer from the Client Network to check if the player is online or not
     * Updates the timer, then calls ping
     */

    @Override
    public void pong() {

       timerTaskNetworkPlayers.setConnected(true);

    }

    @Override
    public void stop() {

        //TODO something
    }

    @Override
    public String getName() {
        return user;
    }

    /**
     * Method called by Observer of model that sends the Model to the Client
     * @param m updated Model
     */
    @Override
    public void update(Model m) {
        sendModel(m);
    }

    /**
     * Thread that manages the sending the List of WindowPatternCard
     */

    private class MyRunnableWindow implements Runnable{

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

    /**
     * Thread that sends the result of the login phase to the Client
     */
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

    /**
     * Thread that sends the privateCard to the Client
     */

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

    /**
     * Thread that sends the updated Model to the Client
     */
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

    /**
     *Thread that sends the answer from Controller to the Client
     */
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

    /**
     * Thread that sends the PlayerZone to the Client
     */
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

    /**
     * Thread that notifies that Client that a new playe is in the game
     */
    private class MyRunnableAddedPlayer implements Runnable {
        volatile String username;
        public MyRunnableAddedPlayer(String name) {
            username = name;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending player zone");
                skeleton.sendAddedPlayer(username);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Thread that sends the CurrentMenu to the Client
     */

    private class myRunnableCurrentMenu implements Runnable {

        volatile String name;

        public myRunnableCurrentMenu(String name) {

            this.name = name;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending player zone");
                skeleton.sendCurrentMenu(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Thread that sends the ping message to the client
     * Updates timer
     */
    private class myRunnablePing implements Runnable {

        public myRunnablePing() {
        }


        @Override
        public void run() {

            //LOGGER.log(Level.WARNING, "Arrived pong");
            try{
                skeleton.pong();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}


