
package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.fileConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.ClientAcceptorRemote;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.ClientManagerRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClientViewRMI class
 * Concrete implementation of ClientView that manages the RMI connection
 */
public class ClientViewRMI extends ClientView {

    private ViewInterface concreteClientView;

    private int RMIPORTServer;

    //private int RMIPORTClient;

    private String address;

    private int id;

    private ClientManagerRemote stub;

    private String username;

    private static final Logger LOGGER = Logger.getLogger(ClientViewRMI.class.getName());

    /**
     * Constructor
     * @param concreteClientView instance of ViewInterface
     * @param data class that contains all information about connection
     */
    public ClientViewRMI(ViewInterface concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;

        RMIPORTServer = data.getServerRMIPORT();

        //RMIPORTClient =data.getClientRMIPORT();

        address = data.getIp();

        id = 0;

        LOGGER.setLevel(Level.INFO);

        register(concreteClientView);

    }

    /**
     * Method that tries to connect client to Server in RMI
     */

    @Override
    public void connect(){

        getStub();
    }


    /**
     * Method called to get the Stub
     * Takes the id and put ii inside the name in bind()
     */

    private void getStub(){

        //Takes the stub
        Registry registry = null;

        try {

            registry = LocateRegistry.getRegistry(address, RMIPORTServer);

            ClientAcceptorRemote stubAcceptor;
            //Looking up the registry for the remote object
            stubAcceptor = (ClientAcceptorRemote) registry.lookup("ClientManagerRemote");

            //Creates the skeleton
            ClientViewRMIRemote clientViewRMIRemote = new ClientViewRMIRemote(this);

            //Update the stubAcceptor into stub for this connection
            stub = stubAcceptor.connect(clientViewRMIRemote);

            LOGGER.log(Level.SEVERE,"Took the stub");

            //call connect method to the real stub
            stub.connect();

            id = stub.getAvailableId();

        } catch (RemoteException | NotBoundException e) {

            LOGGER.log(Level.SEVERE, "Could not reach server: {0}", e.getMessage());
            System.err.println("Enable to reach the Server(first stub), reset and try again");
        }
    }

    /**
     * Method called to show the login screen
     */

    @Override
    public void requestedLogin() {

        LOGGER.log(Level.SEVERE,"Now you are connected in RMI to Server");

        concreteClientView.showLoginScreen();

    }

    /**
     * Method that sends to the Server the username to login
     * @param name username of the player
     */

    @Override
    public void login(String name) {

        try {

            stub.login(name);

        } catch (RemoteException e) {

            System.err.println("Enable to reach the Server, insert name again");
            concreteClientView.showLoginScreen();
        }
    }

    /**
     * Method that receives the result of the login phase, then calls the view
     * @param l result of login phase (True = logged, False = not logged)
     * @param name username of the player
     */

    @Override
    public void logged(Boolean l, String name) {

        if (l){

            username = name;

            concreteClientView.showLoggedScreen();
        }

        else
            concreteClientView.showAlreadyLoggedScreen();
    }

    /**
     * Method that notify to the view that a game is already going on
     */

    @Override
    public void tooManyUsers() {

        concreteClientView.showTooManyUsersScreen();
    }

    /**
     * Method that notify that the connection is not alive
     */

    @Override
    public void disconnected() {

        LOGGER.log(Level.SEVERE,"Client disconnected");

        concreteClientView.showDisconnectScreen();

        System.exit(0);
    }

    /**
     * Method called by client to disconnect
     */

    @Override
    public void disconnect() {

        try {

            stub.disconnect(username);

        } catch (RemoteException e) {

            System.err.println("Enable to reach the Server");
        }
    }

    /**
     * Method that notify to the View the arrive of the list of WindowPatternCard
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of WindowPatternCard
     */

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        this.id = id;

        LOGGER.log(Level.SEVERE,"server is asking a window pattern");

        concreteClientView.showWindowPattern(user, id, windowDeck);

    }

    /**
     * Method that sends to the Server an actionEventWindow
     * @param actionEventWindow information about the chosen WindowPatternCard
     */

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,actionEventWindow.getName()+" is answering windowpattern");

        try {

            stub.chosenWindowPattern(actionEventWindow);

        } catch (RemoteException e) {

            System.err.println("Enable to reach the Server");
        }
    }

    /**
     * Method that notify ro View the arrive of PrivateCard
     * @param privateCard chosen privateCard from Server
     */

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) {

        concreteClientView.showPrivateCard(username, privateCard);
    }

    /**
     * Method that notify to the View the new Model arrived
     * @param m new Model arrived from Controller
     */
    @Override
    protected void notify(Model m) {

        LOGGER.log(Level.SEVERE,"Arrived Model from Controller");

        super.notify(m);
    }

    /**
     * Method that sends to the Server an actionEvent
     * @param actionEvent action made by the player
     */

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {

        LOGGER.log(Level.SEVERE,"sending an action event from view");

        try {

            stub.sendActionEventFromView(actionEvent);

        } catch (RemoteException e) {

            System.err.println("Enable to reach the Server");
        }
    }

    /**
     * Method that sends to the View the answer
     * @param answer String of answer by Controller
     */

    @Override
    public void sendAnswerFromController(String answer) {

        concreteClientView.showAnswerFromController(username, answer);
    }

    /**
     * Method that sends to the View the playerZone
     * @param name username of the player
     * @param playerZone Information about the player (his turn or not, id..)
     */

    @Override
    public void sendBeginTurn(String name, PlayerZone playerZone) {

        concreteClientView.showSetPlayerMenu(name, playerZone);
    }

    /**
     * Method that notify to the player that a new player joins the game
     * @param field1 username of the player
     */

    @Override
    public void sendAddedPlayer(String field1) {

        concreteClientView.showAddedPlayer(field1);
    }

    /**
     * Method that notify the View of showCurrentMenu
     * @param name username of the player
     */

    @Override
    public void sendCurrentMenu(String name) {

        concreteClientView.showCurrentMenu(name);
    }

    /**
     * Method that checks if the connection is alive
     */

    @Override
    public void pong() {

        try {

            stub.pong();

        } catch (RemoteException e) {

            System.err.println("Not able to reach the Server");

        }
    }

    /**
     * Method that show the score for each player
     * @param username username of the player
     * @param score points for each player
     * @param winner username of the winner
     * @param scoreWinner points of the winner
     */

    @Override
    public void endGame(String username, int score, String winner, int scoreWinner) {

        concreteClientView.showEndGame(username, score, winner, scoreWinner);

    }

    /**
     * Method not called in this implementation
     * @param actionEventPlayer information about player connection
     */

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method that receive an actionEvent from View and sends it to the Controller
     * @param actionEvent action made by the player
     */

    @Override
    public void updateAction(ActionEvent actionEvent) {

        sendActionEventFromView(actionEvent);
    }

    /**
     * Method not called in this implementation
     * @param actionEventWindow windowPatternCard received from Client
     */

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method not called in this implementation
     * @param beginGame information that says that the game can start
     */

    @Override
    public void updateBeginGame(Boolean beginGame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Method not called in this implementation
     * @param timerEnd Information about the timerEnd of player
     */

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}