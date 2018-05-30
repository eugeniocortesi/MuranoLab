package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManagerSocket extends ClientManager {

    private ListenerClientManager listenerClientManager;
    private Socket socket;
    private ServerBase server;
    private boolean logged;
    private String user;
    private int id;

    private DataOutputStream writer;

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

    public void sendMessage(String messageSent) {

        if(messageSent == null ){
            return;
        }
        try{
            //TODO CHANGE SOMETHING
            this.writer.writeBytes(messageSent + "\n");
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
        System.out.println("Server connected");
        listenerClientManager.listen();
        //sendAvailableId();
        //requestedLogin();
    }

    public void sendAvailableId(){

        server.addClientManager(this);
        System.out.println("I'm adding " + server.lobbySize() + " elements" );
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
            System.out.println("Too many users logged");
            DataMessage dataMessage = new DataMessage("too_many_users", name);
            sendMessage(dataMessage.serializeClassMessage());

        } else {
            boolean result = server.addView(name, this);
            if (result == false) {
                System.out.println("not logged");
                DataMessage dataMessage = new DataMessage("not_logged", name);
                sendMessage(dataMessage.serializeClassMessage());
                listenerClientManager.listen();
            }else {
                System.out.println("Logged");

                DataMessage dataMessage = new DataMessage("logged", name);
                dataMessage.dump();
                this.user= name;
                sendMessage(dataMessage.serializeClassMessage());
            }
        }
        //listenerClientManager.listen();
    }

    @Override
    public void logged(Boolean l, String name) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

    }

    @Override
    public void chosenWindowPattern(String user, WindowPatternCard windowcard) {

        System.out.println("I have received one windowcard from "+user);
    }
}
