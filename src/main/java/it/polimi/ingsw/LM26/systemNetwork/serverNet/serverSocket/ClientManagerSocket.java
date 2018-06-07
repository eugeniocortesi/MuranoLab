package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowInitialMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void run(){
        connect();
    }



    @Override
    public void connect() {
        LOGGER.log(Level.WARNING,"Server connected");
        listenerClientManager.listen();
        //sendAvailableId();
        //requestedLogin();
    }

    public void sendAvailableId(){

        server.addClientManager(this);

        LOGGER.log(Level.SEVERE,"I'm adding " + server.lobbySize() + " elements" );
        id = getAvailableId();
        ConnectMessage message = new ConnectMessage("connected", id );
        message.dump();
        sendMessage(message.serializeClassMessage());
        listenerClientManager.listen();
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
        listenerClientManager.listen();
    }

    @Override
    public void login(String name) {
        if (!server.checkNumberUsers()) {
            LOGGER.log(Level.SEVERE,"Too many users logged");

            DataMessage dataMessage = new DataMessage("too_many_users", name);
            sendMessage(dataMessage.serializeClassMessage());

        } else {
            boolean result = server.addView(name, this);
            if (result == false) {
                LOGGER.log(Level.SEVERE,"not logged");
                DataMessage dataMessage = new DataMessage("not_logged", name);
                sendMessage(dataMessage.serializeClassMessage());

            }else {
                LOGGER.log(Level.SEVERE,"Logged");

                DataMessage dataMessage = new DataMessage("logged", name);
                dataMessage.dump();
                this.user= name;
                server.checkPlayers();
                sendMessage(dataMessage.serializeClassMessage());
            }
        }

        listenerClientManager.listen();
    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        LOGGER.log(Level.SEVERE,"server is asking a window pattern");
        WindowInitialMessage windowInitialMessage= new WindowInitialMessage("send_windowlist", user, id, windowDeck);
        sendMessage(windowInitialMessage.serializeClassMessage());
    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,"I have received one windowcard from "+user);

        server.getQueueController().pushMessage(actionEventWindow);
        //TODO ATTENTION LISTEN!
        //server.sendToObservable(actionEventWindow);
        listenerClientManager.listen();
    }
}
