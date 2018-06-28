
package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.ClientManagerRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientViewRMI extends ClientView {

    private ViewInterface concreteClientView;
    private int RMIPORTServer;
    private int RMIPORTClient;
    private String address;
    private int id;
    private ClientManagerRemote stub;
    private String username;

    private static final Logger LOGGER = Logger.getLogger(ClientViewRMI.class.getName());

    public ClientViewRMI(ViewInterface concreteClientView, DataClientConfiguration data){

        this.concreteClientView = concreteClientView;
        RMIPORTServer = data.getServerRMIPORT();
        RMIPORTClient =data.getClientRMIPORT();
        address = data.getIp();
        id = 0;
        register(concreteClientView);
        

        getStub();
    }

    public void connect(){
        bind();
    }


    public void bind(){
        //Creates skeleton

        try{
            ClientViewRMIRemote clientViewRMIRemote = new ClientViewRMIRemote(this);
            ClientViewRemote skeleton = (ClientViewRemote) UnicastRemoteObject.exportObject(clientViewRMIRemote, RMIPORTClient);
            //Registry registry = LocateRegistry.createRegistry(RMIPORTClient);
            Registry registry = LocateRegistry.getRegistry(address, RMIPORTServer );
            registry.bind("ClientViewRemote"+id,  skeleton);
            LOGGER.log(Level.WARNING,"Client ready, created skeleton");
            stub.connect();

        }catch (Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    //TODO start connect before bind, return int and put it inside the name used in bind
    public void getStub(){
        //takes the stub

        // Getting the registry
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(address, RMIPORTServer);
            //Looking up the registry for the remote object
            stub = (ClientManagerRemote) registry.lookup("ClientManagerRemote");
            LOGGER.log(Level.SEVERE,"Took the stub");
            id = stub.getAvailableId();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void requestedLogin() {
        LOGGER.log(Level.SEVERE,"Now you are connected in RMI to Server");
        concreteClientView.showLoginScreen();

    }

    @Override
    public void login(String name) {
        try {
            stub.login(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logged(Boolean l, String name) {
        if (l== true){
            username = name;
            concreteClientView.showLoggedScreen();
        }

        else
            concreteClientView.showAlreadyLoggedScreen();
    }

    @Override
    public void tooManyUsers() {
        concreteClientView.showTooManyUsersScreen();
    }

    @Override
    public void disconnected() {
        LOGGER.log(Level.SEVERE,"Client disconnected");
        concreteClientView.showDisconnectScreen();
    }

    @Override
    public void disconnect() {

        try {
            stub.disconnect(username);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        this.id = id;
        LOGGER.log(Level.SEVERE,"server is asking a window pattern");
        concreteClientView.showWindowPattern(user, id, windowDeck);

    }

    @Override
    public void chosenWindowPattern(ActionEventWindow actionEventWindow) {

        LOGGER.log(Level.SEVERE,actionEventWindow.getName()+" is answering windowpattern");
        try {
            stub.chosenWindowPattern(actionEventWindow);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) {
        //privateCard.printCard();
        concreteClientView.showPrivateCard(username, privateCard);
    }

    @Override
    protected void notify(Model m) {
        LOGGER.log(Level.SEVERE,"Arrived Model from Controller");
        if(m == null)
            System.out.println("Model null");

        System.out.println("playerlist "+ m.getPlayerList());
        for(int i = 0; i<m.getPlayerList().size(); i++){
            System.out.println(m.getPlayerList().get(i)+ "player");
        }

        //TODO DELETE
        System.out.print("On boards Tool cards: " );
        for(int j=0; j<m.getOnBoardCards().getToolArrayList().size(); j++)
            System.out.print(m.getOnBoardCards().getToolArrayList().get(j) +  " ");
        System.out.print("\n");
        super.notify(m);
    }

    @Override
    public void sendActionEventFromView(ActionEvent actionEvent) {

        LOGGER.log(Level.SEVERE,"sending an action event from view");
        try {
            stub.sendActionEventFromView(actionEvent);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendAnswerFromController(String answer) {
        concreteClientView.showAnswerFromController(username, answer);
    }

    @Override
    public void sendBeginTurn(String name, PlayerZone playerZone) {
        concreteClientView.showSetPlayerMenu(name, playerZone);
    }

    @Override
    public void sendAddedPlayer(String field1) {
        //concreteClientView.showAddedPlayer(field1);
        System.out.println("Added new player " +field1 );
    }

    @Override
    public void sendCurrentMenu(String name) {

        concreteClientView.showCurrentMenu(name);
    }

    @Override
    public void pong() {

        try {
            stub.pong();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlayers(ActionEventPlayer actionEventPlayer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateAction(ActionEvent actionEvent) {
        sendActionEventFromView(actionEvent);
    }

    @Override
    public void updateWindowPattern(ActionEventWindow actionEventWindow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateBeginGame(Boolean beginGame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateActionEventTimerEnd(ActionEventTimerEnd timerEnd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}