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
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerGame;
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

public class ClientManagerSocket extends ClientManager {

    private ListenerClientManager listenerClientManager;
    private Socket socket;
    private ServerBase server;
    private boolean logged;
    private String user;
    private int id;
    private DataOutputStream writer;
    private TimerPlayer timerPlayer;
    private TimerTaskActionPlayers timerTaskActionPlayers;
    private TimerTaskNetworkPlayers timerTaskNetworkPlayers;
    private static final Logger LOGGER = Logger.getLogger(ClientManagerSocket.class.getName());


    public ClientManagerSocket(Socket socket, ServerBase server){

        this.socket = socket;
        this.server= server;
        this.logged = false;
        this.user = null;
        this.id = 0;
        try {

            this.writer = new DataOutputStream(this.socket.getOutputStream());

        }catch (IOException e) {

        }

        listenerClientManager = new ListenerClientManager(this, socket);
    }

    @Override
    public String getName(){
        return this.user;
    }

    public void sendMessage(String messageSent) {


        if(messageSent == null ){
            return;
        }
        //LOGGER.log(Level.WARNING, messageSent);
        try{
            //TODO CHANGE SOMETHING
            this.writer.writeBytes(messageSent  + "\n");
        } catch (SocketException se){
            System.err.println("Connection reset");
            stop();
        } catch (IOException e) {
            System.err.println("IOException socket writing");
            stop();

        }

    }



    @Override
    public void run(){
        connect();
    }



    @Override
    public void connect() {
        LOGGER.log(Level.WARNING,"Server connected");
        timerPlayer = new TimerPlayer(this, server);

        listenerClientManager.start();

        //Start TimerTaskNetworkPlayer
        timerTaskNetworkPlayers = timerPlayer.scheduleTNetwork();
        LOGGER.log(Level.WARNING, "Timer network Begin");
        Thread t1 = new Thread(new MyRunnablePing());
        t1.start();

    }

    public void sendAvailableId(){

        server.addClientManager(this);

        LOGGER.log(Level.SEVERE,"I'm adding " + server.lobbySize() + " elements" );
        id = getAvailableId();
        ConnectMessage message = new ConnectMessage("connected", id );
        message.dump();
        sendMessage(message.serializeClassMessage());

    }

    @Override
    public int getAvailableId() {

        return server.lobbySize();
    }

    @Override
    public void requestedLogin() {
        DataMessage dataMessage = new DataMessage("requested_login","Server");
        String message = dataMessage.serializeClassMessage();
        sendMessage(message);
    }

    @Override
    public void login(String name) {
        if (!(server.checkNumberUsers()) && server.isGameIsGoing()) {
            LOGGER.log(Level.SEVERE,"Too many users logged");

            DataMessage dataMessage = new DataMessage("too_many_users", name);
            sendMessage(dataMessage.serializeClassMessage());

        } else if(!server.isGameIsGoing()){
            boolean result = server.addView(name, this);
            if (!result) {
                LOGGER.log(Level.SEVERE,"not logged");
                DataMessage dataMessage = new DataMessage("not_logged", name);
                sendMessage(dataMessage.serializeClassMessage());

            }else {
                LOGGER.log(Level.SEVERE,"Logged");

                DataMessage dataMessage = new DataMessage("logged", name);
                dataMessage.dump();
                this.user= name;


                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, true);
                server.getQueueController().pushMessage(actionEventPlayer);
                server.checkPlayers();

                sendMessage(dataMessage.serializeClassMessage());


            }
        }
        else{

            if( server.getClientManagerList().getClientManager(name)!= null){

                LOGGER.log(Level.SEVERE,"Logged");

                DataMessage dataMessage = new DataMessage("logged", name);
                dataMessage.dump();
                this.user= name;


                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, true);
                server.getQueueController().pushMessage(actionEventPlayer);

                sendMessage(dataMessage.serializeClassMessage());
            }
            else{
                LOGGER.log(Level.SEVERE,"not logged");
                DataMessage dataMessage = new DataMessage("not_logged", name);
                sendMessage(dataMessage.serializeClassMessage());
            }

        }

    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void disconnected() {

        LOGGER.log(Level.SEVERE,"server is disconnecting " + user);
        DataMessage dataMessage = new DataMessage("disconnected", user);
        server.getClientManagerList().removeClientManager(user);
        sendMessage(dataMessage.serializeClassMessage());
    }

    @Override
    public void disconnect(String s) {

        LOGGER.log(Level.SEVERE,s +" client is disconnecting ");
        ActionEventPlayer actionEventPlayer=  new ActionEventPlayer(s, false);
        server.getQueueController().pushMessage(actionEventPlayer);
        //TODO CHECK LISTEN!
        disconnected();
    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        LOGGER.log(Level.SEVERE,"server is asking a window pattern");
        WindowInitialMessage windowInitialMessage= new WindowInitialMessage("send_windowlist", user, id, windowDeck);
        sendMessage(windowInitialMessage.serializeClassMessage());
        timerTaskActionPlayers = timerPlayer.scheduleTActionPlayer();
    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);

        server.getQueueController().pushMessage(actionEventWindow);

        timerTaskActionPlayers.setArrivedMessage(true);

    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard card) {

        card.rewrite();
        PrivateCardMessage privateCardMessage = new PrivateCardMessage("send_privatecard", card);
        String s = privateCardMessage.serializeClassMessage();
        sendMessage(s);
    }

    @Override
    public void sendModel(Model m) {

        m.rewriteBeforeSerializing();
        //String prova = "send_model" + "$" + "\n" + m.serializeClassMessage();
        String s = m.serializeClassMessage();
        ModelMessage modelMessage = new ModelMessage("send_model", s);

        String message = modelMessage.serializeClassMessage();
        System.out.println("message");
        /*DataMessage dataMessage1 = DataMessage.deserializeDataMessage(message);
        String modelString = dataMessage1.getField1();
        Model model = Model.deserializeModelMessage(modelString);
        System.out.println(model);*/

        sendMessage(message);

    }

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {
        LOGGER.log(Level.SEVERE,"I have received one actionEvent from "+user);
        //timerTaskActionPlayers.setArrivedMessage(true);
        server.getQueueController().pushMessage(actionEvent);

    }

    @Override
    public void sendAnswerFromController(String message) {

        LOGGER.log(Level.SEVERE,"server is answering a message");
        DataMessage dataMessage = new DataMessage("send_answer_from_controller", message);
        String s = dataMessage.serializeClassMessage();
        sendMessage(s);

    }

    @Override
    public void sendBeginTurnMessage(String name, PlayerZone playerZone) {

        playerZone.rewrite();

        LOGGER.log(Level.SEVERE,"server is sending playerzone of " + name);
        BeginTurnMessage beginTurnMessage = new BeginTurnMessage("send_beginturnmessage", name, playerZone);
        sendMessage(beginTurnMessage.serializeClassMessage());

        //TODO check it
        //timerPlayer.resetTActionPlayer();
        //timerPlayer.scheduleTActionPlayer();

    }

    @Override
    public void sendAddedPlayer(String name) {

        LOGGER.log(Level.SEVERE,"server is sending a new player");
        DataMessage dataMessage = new DataMessage("added_player", name);
        String s = dataMessage.serializeClassMessage();
        sendMessage(s);
    }

    @Override
    public void sendCurrentMenu(String name) {

        LOGGER.log(Level.SEVERE,"server is sending current menu");
        DataMessage dataMessage = new DataMessage("send_currentmenu", name);
        String s = dataMessage.serializeClassMessage();
        sendMessage(s);

    }

    @Override
    public void sendEndGame(Object score) {
        //TODO it
    }

    @Override
    public void ping() {
        //LOGGER.log(Level.WARNING, "Start ping");
        Thread t = new Thread(new MyRunnablePing());
        t.start();
    }

    @Override
    public void pong() {

        timerTaskNetworkPlayers.setConnected(true);
    }

    @Override
    public void stop() {

        listenerClientManager.end();
    }


    @Override
    public void update(Model m) {

        LOGGER.log(Level.SEVERE,"Updating model from server");
        sendModel(m);
    }

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
