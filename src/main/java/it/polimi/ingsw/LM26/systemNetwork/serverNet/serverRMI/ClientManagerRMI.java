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
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayer;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionPlayers;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskNetworkPlayers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClientManagerRMI class
 * @author Chiara Criscuolo
 * It manages the RMI connection
 */

public class ClientManagerRMI extends ClientManager {

    private ServerBase myserver;

    private ClientViewRemote skeleton;

    private String user;

    private TimerPlayer timerPlayer;

    private TimerTaskActionPlayers timerTaskActionPlayers;

    private TimerTaskNetworkPlayers timerTaskNetworkPlayers;

    private static final Logger LOGGER = Logger.getLogger(ClientManagerRMI.class.getName());

    /**
     * Constructor
     * @param serverBase Server
     */
    public ClientManagerRMI(ServerBase serverBase){

        myserver = serverBase;
        this.user= null;
    }

    /**
     * Method that adds the new client to the lobby
     * Starts the timer Network
     * Then call method "requestedLogin" in the client
     */
    public void connect() {

        timerPlayer = new TimerPlayer(this, myserver);

        myserver.addClientManager(this);
        //Start Network Timer
        timerTaskNetworkPlayers = timerPlayer.scheduleTNetwork();
        LOGGER.log(Level.WARNING, "Timer network Begin");
        Thread t1 = new Thread(new myRunnablePing());
        t1.start();

        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    skeleton.requestedLogin();
                } catch (RemoteException e) {
                    System.err.println("Connection reset");
                }
            }

        });
        t.start();
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
        if (!myserver.checkNumberUsers() && myserver.isGameIsGoing()) {

            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {
                        skeleton.tooManyUsers();
                    } catch (RemoteException e) {
                        System.err.println("Connection reset");
                    }
                }

            });
            t.start();
        }else if (!myserver.isGameIsGoing()){
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
        else{
            boolean result1 = false;
            if( myserver.getClientManagerList().getClientManager(name)!= null){

                 result1 = true;
            }

            LOGGER.log(Level.INFO,"The add result value: " + result1);
            Thread t = new Thread(new MyRunnableLogged(user, result1));
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
                    System.err.println("Connection reset");
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
        timerTaskActionPlayers = timerPlayer.scheduleTActionPlayer();
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

        //TODO CHECK TIMER
        //timerGame.resetTimerActionPlayer();
        //timerTaskActionPlayers = timerGame.scheduleTimerActionPlayer(user);
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
        //timerPlayer.resetTActionPlayer();
        //timerTaskActionPlayers = timerPlayer.scheduleTActionPlayer();
        Thread t = new Thread(new myRunnableCurrentMenu(name));
        t.start();

    }

    /**
     * Method that sends to the player his points
     * @param name username of the player
     * @param score points of the player
     * @param winner username of the winner
     * @param scoreWinner points of the winner
     */

    @Override
    public void sendEndGame(String name, int score, String winner, int scoreWinner) {

        Thread t = new Thread (new myRunnableEnd(name, score, winner, scoreWinner));
        t.start();
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

        System.err.println("Timer out: Connection reset");
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

    public void setSkeleton(ClientViewRemote skeleton) {
        this.skeleton = skeleton;
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
                System.err.println("Connection reset");
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
                System.err.println("Connection reset");
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
                System.err.println("Connection reset");
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
                System.err.println("Connection reset");
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
                System.err.println("Connection reset");
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
                System.err.println("Connection reset");
            }
        }
    }

    /**
     * Thread that notifies that Client that a new playe is in the game
     */
    private class MyRunnableAddedPlayer implements Runnable {
        volatile String username;

        MyRunnableAddedPlayer(String name) {
            username = name;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending player zone");
                skeleton.sendAddedPlayer(username);
            } catch (RemoteException e) {
                System.err.println("Connection reset");
            }
        }
    }

    /**
     * Thread that sends the CurrentMenu to the Client
     */

    private class myRunnableCurrentMenu implements Runnable {

        volatile String name;

        myRunnableCurrentMenu(String name) {

            this.name = name;
        }

        @Override
        public void run() {

            try {
                LOGGER.log(Level.SEVERE,"Sending player zone");

                skeleton.sendCurrentMenu(name);

            } catch (RemoteException e) {
                System.err.println("Connection reset");
            }
        }
    }

    /**
     * Thread that sends the ping message to the client
     * Updates timer
     */
    private class myRunnablePing implements Runnable {

        myRunnablePing() {
        }


        @Override
        public void run() {

            //LOGGER.log(Level.WARNING, "Arrived pong");
            try{
                skeleton.pong();
            } catch (RemoteException e) {
                System.err.println("Connection refused");
            }
        }
    }

    /**
     *  Class that sends score to each player
     */

    private class myRunnableEnd implements Runnable {

        volatile String username;
        volatile int score;
        volatile String winner;
        volatile int scoreWinner;

        public myRunnableEnd(String username, int score, String winner, int scoreWinner) {
            this.username = username;
            this.score = score;
            this.winner = winner;
            this.scoreWinner = scoreWinner;
        }

        @Override
        public void run() {

            try{
                skeleton.endGame(username, score, winner, scoreWinner);
            } catch (RemoteException e) {
                System.err.println("Connection refused");
            }
        }
    }
}


