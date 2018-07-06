package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ModelMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayer;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionPlayers;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskNetworkPlayers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClientManagerSocket class
 * @author Chiara Criscuolo
 * Concrete implementation of ClientManager
 * Manages one socket connection for each client
 */

public class ClientManagerSocket extends ClientManager {

    private ListenerClientManager listenerClientManager;

    private Socket socket;

    private ServerBase server;

    private String user;

    private int id;

    private boolean inActive;

    private DataOutputStream writer;

    private TimerPlayer timerPlayer;

    private TimerTaskActionPlayers timerTaskActionPlayers;

    private TimerTaskNetworkPlayers timerTaskNetworkPlayers;

    private static final Logger LOGGER = Logger.getLogger(ClientManagerSocket.class.getName());

    /**
     * Constructor
     * @param socket socket
     * @param server reference to the Server
     */

    public ClientManagerSocket(Socket socket, ServerBase server){

        LOGGER.setLevel(Level.ALL);

        this.socket = socket;

        this.server= server;

        this.user = null;

        this.id = 0;

        try {

            this.writer = new DataOutputStream(this.socket.getOutputStream());

        }catch (IOException e) {

            System.err.println("Enable to create a Writer, please reset Server");
        }

        listenerClientManager = new ListenerClientManager(this, socket);
    }

    @Override
    public String getName(){
        return this.user;
    }

    /**
     * Method that sends message
     * @param messageSent String
     */

    private void sendMessage(String messageSent) {

        if(messageSent == null ){
            return;
        }

        try{

            this.writer.writeBytes(messageSent  + "\n");

        } catch (SocketException se){

            System.err.println("Connection reset");
            stop();

        } catch (IOException e) {

            System.err.println("IOException socket writing");
            stop();

        }

    }

    /**
     * Called by start
     */

    @Override
    public void run(){
        connect();
    }

    /**
     * Method that manages the request of socket connection
     */

    @Override
    public void connect() {

        timerPlayer = new TimerPlayer(this, server);

        listenerClientManager.start();

        //Start TimerTaskNetworkPlayer
        timerTaskNetworkPlayers = timerPlayer.scheduleTNetwork();

        LOGGER.log(Level.WARNING, "Timer network Begin");

        //Thread t1 = new Thread(new MyRunnablePing());

        //t1.start();

        inActive = true;

        ping();

    }

    /**
     * Method that sends the id number of the connection
     */

    public void sendAvailableId(){

        server.addClientManager(this);

        LOGGER.log(Level.SEVERE,"I'm adding " + server.lobbySize() + " elements" );

        id = getAvailableId();

        ConnectMessage message = new ConnectMessage("connected", id );

        sendMessage(message.serializeClassMessage());

    }

    /**
     * Method that return the id number of the connection
     * @return id number of the connection
     */

    @Override
    public int getAvailableId() {

        return server.lobbySize();
    }

    /**
     * Method that sends a request of login to the Client
     */

    @Override
    public void requestedLogin() {

        DataMessage dataMessage = new DataMessage("requested_login","Server");

        String message = dataMessage.serializeClassMessage();

        sendMessage(message);
    }

    /**
     * Method that manages the login phase
     * @param name username of player
     */

    @Override
    public void login(String name) {
        if (!(server.checkNumberUsers()) && server.isGameIsGoing()) {

            //case that a game is already going on

            LOGGER.log(Level.SEVERE,"Too many users logged");

            DataMessage dataMessage = new DataMessage("too_many_users", name);

            sendMessage(dataMessage.serializeClassMessage());

        } else if(!server.isGameIsGoing()){

            //added the ClientManager to the HashMap
            boolean result = server.addView(name, this);

            if (!result) {

                //The addiction returned false
                LOGGER.log(Level.SEVERE,"not logged");

                DataMessage dataMessage = new DataMessage("not_logged", name);

                sendMessage(dataMessage.serializeClassMessage());

            }else {
                //The addiction returned true
                LOGGER.log(Level.SEVERE,"Logged");

                DataMessage dataMessage = new DataMessage("logged", name);

                this.user= name;

                //Sends notification to Controller that a new player is logged
                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, true);

                server.getQueueController().pushMessage(actionEventPlayer);

                //Checks if the game has to start
                server.checkPlayers();

                sendMessage(dataMessage.serializeClassMessage());

            }
        }
        else{

            //The game is going on
            if( server.getClientManagerList().getClientManager(name)!= null){

                LOGGER.log(Level.SEVERE,"Logged");

                DataMessage dataMessage = new DataMessage("logged", name);

                dataMessage.dump();

                this.user= name;

                //A player has returned to play
                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, true);

                server.getQueueController().pushMessage(actionEventPlayer);

                sendMessage(dataMessage.serializeClassMessage());
            }
            else{
                //the player was not playing and for him is not possible to return
                LOGGER.log(Level.SEVERE,"not logged");

                DataMessage dataMessage = new DataMessage("not_logged", name);

                sendMessage(dataMessage.serializeClassMessage());
            }
        }
    }


    /**
     * Not used in this implementation
     * @param l result of login (true = logged, false = not logged)
     * @param name username of player
     */

    @Override
    public void logged(Boolean l, String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Notify the client that is disconnected
     */

    @Override
    public void disconnected() {

        LOGGER.log(Level.SEVERE,"server is disconnecting " + user);

        DataMessage dataMessage = new DataMessage("disconnected", user);

        //Remove it
        server.getClientManagerList().removeClientManager(user);

        sendMessage(dataMessage.serializeClassMessage());
    }

    /**
     * Notify Server that a client is disconnecting
     * @param s username of the player
     */

    @Override
    public void disconnect(String s) {

        LOGGER.log(Level.SEVERE,s +" client is disconnecting ");

        //Notify Controller that a player is disconnected
        ActionEventPlayer actionEventPlayer=  new ActionEventPlayer(s, false);

        server.getQueueController().pushMessage(actionEventPlayer);

        disconnected();
    }

    /**
     * Method that sends a list of WindowPatternCard to the Client
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of WindowPatternCard
     */

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        LOGGER.log(Level.SEVERE,"server is asking a window pattern");

        WindowInitialMessage windowInitialMessage= new WindowInitialMessage("send_windowlist", user, id, windowDeck);

        sendMessage(windowInitialMessage.serializeClassMessage());

        //Starts the timer
        timerTaskActionPlayers = timerPlayer.scheduleTActionPlayer();
    }

    /**
     * Receive an actionEventWindow form Client and push it to Controller
     * @param actionEventWindow ActionEventWindow thant contains the WindowPatternCard chosen
     */

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);

        server.getQueueController().pushMessage(actionEventWindow);

        //For the timer: the answer is arrived
        timerTaskActionPlayers.setArrivedMessage(true);

    }

    /**
     * Sends the private Card to the Client
     * @param card PrivateCard
     */

    @Override
    public void sendPrivateCard(ObjectivePrivateCard card) {

        //Json needs it
        card.rewrite();

        PrivateCardMessage privateCardMessage = new PrivateCardMessage("send_privatecard", card);

        String s = privateCardMessage.serializeClassMessage();

        sendMessage(s);
    }

    /**
     * Method that sends to the client m
     * @param m new Model
     */

    @Override
    public void sendModel(Model m) {

        //Json needs it
        m.rewriteBeforeSerializing();

        String s = m.serializeClassMessage();

        ModelMessage modelMessage = new ModelMessage("send_model", s);

        String message = modelMessage.serializeClassMessage();

        sendMessage(message);

    }

    /**
     * Receive an actionEvent and push it to Controller
     * @param actionEvent ActionEvent : chosen move of the player
     */

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {

        LOGGER.log(Level.SEVERE,"I have received one actionEvent from "+user);

        server.getQueueController().pushMessage(actionEvent);

    }

    /**
     * Sends a message to the Client
     * @param message String from Controller
     */

    @Override
    public void sendAnswerFromController(String message) {

        LOGGER.log(Level.SEVERE,"server is answering a message");

        DataMessage dataMessage = new DataMessage("send_answer_from_controller", message);

        String s = dataMessage.serializeClassMessage();

        sendMessage(s);

    }

    /**
     * Method that sends the playerZone to the Client
     * @param name username of the player
     * @param playerZone PlayerZone : all information about his Zone
     */

    @Override
    public void sendBeginTurnMessage(String name, PlayerZone playerZone) {

        //Json needs it
        playerZone.rewrite();

        LOGGER.log(Level.SEVERE,"server is sending playerzone of " + name);

        BeginTurnMessage beginTurnMessage = new BeginTurnMessage("send_beginturnmessage", name, playerZone);

        sendMessage(beginTurnMessage.serializeClassMessage());

    }

    /**
     * Method that notify the Client that a new player has joined the game
     * @param name username of the added player
     */

    @Override
    public void sendAddedPlayer(String name) {

        LOGGER.log(Level.SEVERE,"server is sending a new player");

        DataMessage dataMessage = new DataMessage("added_player", name);

        String s = dataMessage.serializeClassMessage();

        sendMessage(s);
    }

    /**
     * Method that sends to the client a notify of showCurrentMenu
     * @param name username of the player
     */

    @Override
    public void sendCurrentMenu(String name) {

        LOGGER.log(Level.SEVERE,"server is sending current menu");

        DataMessage dataMessage = new DataMessage("send_currentmenu", name);

        String s = dataMessage.serializeClassMessage();

        sendMessage(s);

    }

    /**
     * Method that sends score to the Client
     * @param score final points of the player
     */

    @Override
    public void sendEndGame(String username, int score, String winner, int scoreWinner) {

        LOGGER.log(Level.SEVERE,"server is sending score");

        EndMessage dataMessage = new EndMessage("endGame", username, score, winner, scoreWinner);

        String s = dataMessage.serializeClassMessage();

        sendMessage(s);
    }

    /**
     * Method that checks that the connection is alive
     */

    @Override
    public void ping() {

        if(inActive){

            LOGGER.log(Level.INFO, "Sending ping");

            Thread t = new Thread(new MyRunnablePing());

            t.start();
        }

    }

    /**
     * When a pong arrives timerNetwork has to be notified
     */

    @Override
    public void pong() {

        LOGGER.log(Level.INFO, "Pong arrived");

        timerTaskNetworkPlayers.setConnected(true);
    }

    /**
     * Interrupts the listen process
     */

    @Override
    public void stop() {

        listenerClientManager.end();
        inActive = false;
    }

    /**
     * Update from Model that is changed
     * @param m new Model
     */

    @Override
    public void update(Model m) {

        LOGGER.log(Level.SEVERE,"Updating model from server");

        sendModel(m);
    }

    /**
     * Class that sends ping message to the Client
     */

    private class MyRunnablePing implements Runnable {

        public MyRunnablePing() {
        }

        @Override
        public void run(){

            DataMessage dataMessage = new DataMessage("ping", "ping");

            sendMessage(dataMessage.serializeClassMessage());
        }
    }
}
