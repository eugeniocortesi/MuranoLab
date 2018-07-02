
package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientSocket;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataClientConfiguration;
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

/**
 * ClientViewSocket class
 * @author Chiara Criscuolo
 * Manages the connection in Socket for each client
 */

public class ClientViewSocket extends ClientView {

    private ListenerClientView listenerClientView;

    private ViewInterface concreteClientView;

    private int SOCKETPORT;

    private static String address;

    private Socket socket;

    private PrintWriter outSocket;

    private String username;

    private static final Logger LOGGER = Logger.getLogger(ClientViewSocket.class.getName());

    /**
     * Class constructor
     * @param concreteClientView reference to the view
     * @param data information abot connection
     */

    public ClientViewSocket(ViewInterface concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;

        SOCKETPORT = data.getClientSOCKETPORT();

        address = data.getIp();

        register(concreteClientView);

        LOGGER.setLevel(Level.OFF);

    }

    /**
     * Method that ask the Socket connection to Server
     */

    @Override
    public void connect() {

        try {
            LOGGER.log(Level.SEVERE, "I'm trying to connect");

            socket = new Socket(address, SOCKETPORT);

            outSocket = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            listenerClientView = new ListenerClientView(this, socket);

            listenerClientView.start();

            connected();

        } catch (Exception e) {
            System.err.println("Client not able to connect, plese try again.");

            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Socket not closed");
            }
        }
    }

    /**
     * Method that confirms that the client is connected to Server in Socket
     */

    public void connected(){

        LOGGER.log(Level.SEVERE,"Client connected");

        ConnectMessage connectMessage = new ConnectMessage("connected", 0);

        connectMessage.dump();

        outSocket.println(connectMessage.serializeClassMessage());
    }

    /**
     * Method that receives the id connection and calls requestedLogin()
     * @param id id number of connection of the player
     */

    public void getAvailableId(int id){

        requestedLogin();
    }

    /**
     * Method that calls in the View the login Screen
     */

    @Override
    public void requestedLogin() {

        concreteClientView.showLoginScreen();

    }

    /**
     * Method that sends to Server the username of the player
     * @param name username of the player
     */

    @Override
    public void login(String name) {

        DataMessage message = new DataMessage("login", name);

        outSocket.println(message.serializeClassMessage());

    }

    /**
     * Method that receive the result of login phase
     * @param l result of login phase (True = logged, False = not logged)
     * @param name username of the player
     */

    @Override
    public void logged(Boolean l, String name) {

        if (l){

            this.username = name;

            concreteClientView.showLoggedScreen();
        }
        else{

            concreteClientView.showAlreadyLoggedScreen();
        }

    }

    /**
     * Method that receives as answer from login phase that a game is already started
     */
    @Override
    public void tooManyUsers() {

        concreteClientView.showTooManyUsersScreen();

    }

    /**
     * Method that sends to the Server a disconnect message
     */

    @Override
    public void disconnect() {

        LOGGER.log(Level.SEVERE,"Client trying to disconnect");

        DataMessage dataMessage = new DataMessage("disconnect", username);

        outSocket.println(dataMessage.serializeClassMessage());

    }

    /**
     * Method that notify to the server that is disconnected
     */

    @Override
    public void disconnected() {

        LOGGER.log(Level.SEVERE,"Client disconnected");

        concreteClientView.showDisconnectScreen();
    }

    /**
     * Method that receives from server the list of WindowPatternCard
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of WindowPatternCard
     */

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        LOGGER.log(Level.SEVERE,"server is asking a window pattern");

        concreteClientView.showWindowPattern(user, id, windowDeck);

    }

    /**
     * Method that sends to the Server the chosen WindowPatternCard
     * @param actionEventWindow information about the chosen WindowPatternCard
     */

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        WindowAnswerMessage message = new WindowAnswerMessage("send_windowcard", actionEventWindow);

        outSocket.println(message.serializeClassMessage());

    }

    /**
     * method that receive the private Card and call view to show it
     * @param privateCard chosen privateCard from Server
     */

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) {

        concreteClientView.showPrivateCard(username, privateCard);

    }

    /**
     * Method that sends to the Server the actionEvent
     * @param actionEvent action made by the player
     */

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {

        actionEvent.rewrite();

        EventMessage message = new EventMessage("send_actionevent_from_view", actionEvent);

        LOGGER.log(Level.WARNING, "sending answer from view to Controller");

        outSocket.println(message.serializeClassMessage());
    }

    /**
     * Method that receives the answer by Controller
     * @param answer String of answer by Controller
     */

    @Override
    public void sendAnswerFromController(String answer) {

        concreteClientView.showAnswerFromController(username, answer);

    }

    /**
     * Method that receives the playerZone from Server
     * @param name username of the player
     * @param playerZone Information about the player (his turn or not, id..)
     */

    @Override
    public void sendBeginTurn(String name, PlayerZone playerZone) {

        LOGGER.log(Level.SEVERE,"Show set player menu arrived from Controller");

        concreteClientView.showSetPlayerMenu(name, playerZone);
    }

    /**
     * Method that notify that a new player joins the game
     * @param field1 username of the player
     */

    @Override
    public void sendAddedPlayer(String field1) {

        concreteClientView.showAddedPlayer(field1);
    }

    /**
     * Method that call view to show the currentMenu
     * @param name username of the player
     */

    @Override
    public void sendCurrentMenu(String name) {

        LOGGER.log(Level.SEVERE,"Show current menu is arrived from Controller");

        concreteClientView.showCurrentMenu(name);
    }

    /**
     * Method that receive a ping message and sends pong to the Server
     */

    @Override
    public void pong() {

        DataMessage dataMessage = new DataMessage("pong", "pong");

        LOGGER.log(Level.SEVERE, "Pong arrived");

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                outSocket.println(dataMessage.serializeClassMessage());
            }
        });
        t.start();

    }

    /**
     * Method that notify to the Vuew the model arrived
     * @param m new Model arrived
     */
    @Override
    protected void notify(Model m) {

        LOGGER.log(Level.SEVERE,"Model is arrived from Controller");

        super.notify(m);
    }

    /**
     * Method not called in this implementation of ObservableSimple
     * @param actionEventPlayer Information about the player connection
     */

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method that receive an actionEvent from View and sends it to the Server
     * @param actionEvent information about the action made by the player
     */

    @Override
    public void updateAction(ActionEvent actionEvent) {

        sendActionEventFromView(actionEvent);
    }

    /**
     * Method called by View to send the ActionEventWindow
     * @param actionEventWindow information about the chosen WindowPatternCard
     */

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method not called in this implementation of ObservableSimple
     * @param beginGame boolean about the game start
     */

    @Override
    public void updateBeginGame(Boolean beginGame) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method not called in this implementation of ObservableSimple
     * @param timerEnd event that contains if the client has finished his time or not
     */

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {

        throw new UnsupportedOperationException("Not supported yet.");
    }
}