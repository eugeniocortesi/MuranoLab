
package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.EventMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientViewSocket extends ClientView {

    private ListenerClientView listenerClientView;
    private ViewInterface concreteClientView;
    private int SOCKETPORT;

    private static String address;
    private Socket socket;
    private BufferedReader inSocket;
    private PrintWriter outSocket;
    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private String username;
    private int id;

    private static final Logger LOGGER = Logger.getLogger(ClientViewSocket.class.getName());


    public ClientViewSocket(ViewInterface concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;
        SOCKETPORT = data.getClientSOCKETPORT();
        address = data.getIp();

        register(concreteClientView);
       /* Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        LOGGER.addHandler(handlerObj);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);*/

        //listenerClientView.listen();
    }

    @Override
    public void connect() {

        try {
            LOGGER.log(Level.SEVERE, "I'm trying to connect");

            socket = new Socket(address, SOCKETPORT);
            //canali di comunicazione
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
            listenerClientView = new ListenerClientView(this, socket);
            connected();
            listenerClientView.listen();

            LOGGER.log(Level.SEVERE,"ClientImplementationSocket connected");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();

            // Always close it:
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void connected(){
        LOGGER.log(Level.SEVERE,"Client connected");
        ConnectMessage connectMessage = new ConnectMessage("connected", 0);
        connectMessage.dump();
        outSocket.println(connectMessage.serializeClassMessage());
    }

    public void getAvailableId(int id){
        this.id= id;
        requestedLogin();
    }


    @Override
    public void requestedLogin() {
        concreteClientView.showLoginScreen();

    }

    @Override
    public void login(String name) {
        DataMessage message = new DataMessage("login", name);
        message.dump();
        outSocket.println(message.serializeClassMessage());
        listenerClientView.listen();
    }

    @Override
    public void logged(Boolean l, String name) {
        if (l==true){
            this.username = name;
            concreteClientView.showLoggedScreen();
        }
        else{
            concreteClientView.showAlreadyLoggedScreen();
        }

    }

    @Override
    public void tooManyUsers() {
        concreteClientView.showTooManyUsersScreen();

    }

    @Override
    public void disconnect() {
        concreteClientView.showDisconnectScreen();
    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        LOGGER.log(Level.SEVERE,"server is asking a window pattern");
        this.id = id;
        concreteClientView.showWindowPattern(user, id, windowDeck);

    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {
        WindowAnswerMessage message = new WindowAnswerMessage("send_windowcard", actionEventWindow);
        outSocket.println(message.serializeClassMessage());

    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) {
        //TODO remove it
        privateCard.printCard();
        //concreteClientView.showPrivateCard(username, privateCard);

    }


    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {
        EventMessage message = new EventMessage("send_actionevent_from_view", actionEvent);
        outSocket.println(message.serializeClassMessage());
        listenerClientView.listen();
    }

    @Override
    public void sendAnswerFromController(String answer) {
        concreteClientView.showAnswerFromController(answer);

    }

    @Override
    public void sendBeginTurn(String name, PlayerZone playerZone) {
        concreteClientView.showSetPlayerMenu(name, playerZone);
    }

    @Override
    protected void notify(Model m) {
        LOGGER.log(Level.SEVERE,"Model is arrived from Controller");
        super.notify(m);
    }
}