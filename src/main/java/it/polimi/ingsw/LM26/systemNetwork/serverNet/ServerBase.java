package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.*;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.fileConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.RMIAcceptor;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket.SocketAcceptor;
import it.polimi.ingsw.LM26.fileConfiguration.TimerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerGame;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ServerBase class
 * @author Chiara Criscuolo
 * It manages all connection in a HashMap and has a reference to Controller
 * It has a lobby to collect all connections
 */

public class ServerBase extends ViewGameInterface {

    private ObservableSimple model;

    private Observer controller;

    private RMIAcceptor rmiAcceptor;

    private SocketAcceptor socketAcceptor;

    private ClientManagerList clientManagerList;

    private ArrayList<ClientManager> lobby;

    private Receiver receiver;

    private MessageQueue queueController;

    private VisitorInt visitor;

    private DataServerConfiguration dataServerConfiguration;

    private TimerConfiguration timerConfiguration;

    private boolean gameIsGoing;

    private TimerGame timerGame;

    private static final Logger LOGGER = Logger.getLogger(ServerBase.class.getName());

    /**Constructor
     * Called by controller
     * @param controllerInt controller interface
     */

    public ServerBase(Observer controllerInt){

        LOGGER.setLevel(Level.OFF);

        controller = controllerInt;

        gameIsGoing = false;

        lobby = new ArrayList<ClientManager>();

        dataServerConfiguration = new DataServerConfiguration().implementation();

        timerConfiguration = TimerConfiguration.implementation();

        LOGGER.log(Level.WARNING, "SocketPort " +dataServerConfiguration.getSOCKETPORT()+ " ClientRMI " + dataServerConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataServerConfiguration.getServerRMIPORT());

        try {
            InetAddress ip = InetAddress.getLocalHost();

            System.out.println("Server address: " +ip);

        }catch(UnknownHostException he){

            he.printStackTrace();
        }

        clientManagerList = new ClientManagerList(this);

        queueController = new MessageQueue();

        visitor = new VisitorMessage();

        receiver = new Receiver(queueController, visitor);

    }

    /**
     * Method that starts all the connectionAcceptor
     * @param controller instance of Controller
     * @param model instance of Model
     */

    @Override
    public void startAcceptor(Observer controller, ObservableSimple model){

        rmiAcceptor = new RMIAcceptor(this, dataServerConfiguration);

        rmiAcceptor.bind();

        socketAcceptor = new SocketAcceptor(this, dataServerConfiguration);

        socketAcceptor.start();

        receiver.getVisitorInt().getObservable().register(controller);

        LOGGER.log(Level.WARNING,"Registered Observer");

        this.model = model;

        receiver.start();
    }

    public TimerConfiguration getTimerConfiguration() {
        return timerConfiguration;
    }

    public ClientManagerList getClientManagerList() {
        return clientManagerList;
    }

    public void setGameIsGoing(boolean gameIsGoing) {
        this.gameIsGoing = gameIsGoing;
    }

    public boolean isGameIsGoing() {
        return gameIsGoing;
    }

    public MessageQueue getQueueController() {
        return queueController;
    }

    public Observer getController() {
        return controller;
    }

    /**
     * Method that add a new player to the hashMap
     * @param s username of the player
     * @param clientManager reference to the client connection
     * @return boolean about the add method
     */

    public synchronized boolean addView(String s, ClientManager clientManager){

        if(gameIsGoing)

            return false;

        if(checkNumberUsers()){

            boolean b = clientManagerList.addClientManager(s, clientManager);

            if(b)
                updatePlayers(s);

            if(clientManagerListSize()== 2){

                LOGGER.log(Level.WARNING,"Timer game started!");

                timerGame = new TimerGame(this, timerConfiguration.getTimerGameEnd());

                timerGame.scheduleTimerPlayer();
            }
            return b;
        }
        return false;
    }

    /**
     * Method that checks if s is already present, if not add it
     * @param s username of the player
     */
    private synchronized void updatePlayers(String s){

        Iterator iterator = clientManagerList.getManagerHashMap().entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry couple = (Map.Entry)iterator.next();

            if(couple.getValue()!= null){

                ClientManager cm = (ClientManager) couple.getValue();

                if(!(couple.getKey().equals(s)))
                    cm.sendAddedPlayer(s);
            }
            else{

                LOGGER.log(Level.WARNING,"Client manager null");
            }

            LOGGER.log(Level.WARNING,"Updated players");
        }
    }

    /**
     * Method that add the connection to the lobby
     *
     * @param clientManager reference to the client connection
     */

    public synchronized void addClientManager(ClientManager clientManager){
        lobby.add(clientManager);
    }

    /**
     * Method that return the lobbySize
     * @return lobbySize
     */

    public int lobbySize(){
        return lobby.size();
    }

    /**
     * Method that return HashMap size
     * @return clientManagerListSize
     */

    public int clientManagerListSize(){
        return clientManagerList.size();
    }

    /**
     * Method that checks is players connected are less then 4
     * @return boolean (true = less than 4)
     */

    public boolean checkNumberUsers(){
        //if(gameIsGoing)
          //  return true;
        if (clientManagerListSize()<4)
            return true;


        /*else if (!playing) {
            Model model = SingletonModel.singletonModel();
            System.out.println("Inserisci nome: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String nome = br.readLine();
                System.out.println("Name preso");
                showWindowPattern(nome, 0, model.getDecks().getWindowPatternCardDeck());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return false;
    }

    /**
     * Method that return if a game has started
     * @return boolean (true = game has started)
     */

    public boolean isGameGoing(){
        return gameIsGoing;
    }

    /**
     * Method that registry each ClientManager to the Model
     */
    public void registerModel(){

        Iterator iterator = clientManagerList.getManagerHashMap().entrySet().iterator();

        while(iterator.hasNext()){

            Map.Entry couple = (Map.Entry)iterator.next();

            //System.out.println(couple.getKey());

            if((ObserverSimple) couple.getValue()!= null)
                model.register((ObserverSimple) couple.getValue());

        }
    }

    /**
     * Method that returns the number of logged clients
     */

    public void checkPlayers(){
        clientManagerList.checkNumberLogged();
    }

    /**
     * Method called to send list of WindowPatternCard using ClientManager
     * @param user username of the player
     * @param id id number of the player
     * @param windowDeck list of windowPatternCard
     */

    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {

        if(clientManagerList.getClientManager(user)!= null)

        clientManagerList.getClientManager(user).choseWindowPattern(user, id, windowDeck);

    }

    /**
     * Method called to send PrivateCard to the View using ClientManager
     * @param name username of the player
     * @param privateCard privateCard chosen for the player
     */

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {

        if(clientManagerList.getClientManager(name)!=null)

        clientManagerList.getClientManager(name).sendPrivateCard(privateCard);
    }

    /**
     * Method that sends the playerZone using ClientManager
     * @param name username of the player
     * @param player playerZone of the player
     */

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {

        if(clientManagerList.getClientManager(name)!=null)

        clientManagerList.getClientManager(name).sendBeginTurnMessage(name, player);
    }

    /**
     * Method that calls to the View the currentMenu using ClientManger
     * @param name username of the player
     */

    @Override
    public void showCurrentMenu(String name) {

        if(clientManagerList.getClientManager(name)!=null)

        clientManagerList.getClientManager(name).sendCurrentMenu(name);
    }

    /**
     * Method that sends the answer from Controller to the View using ClientManager
     * @param name username of the player
     * @param answer string of answer from Controller
     */

    @Override
    public void showAnswerFromController(String name, String answer) {

        if(clientManagerList.getClientManager(name)!=null)

            clientManagerList.getClientManager(name).sendAnswerFromController(answer);
    }

    /**
     * Method that sends to the View final score of the game using ClientManager
     * @param name username of the player
     * @param score final score of the game
     */

    @Override
    public void showEndGame(String name, int score, String winner, int scoreWinner) {

        if(clientManagerList.getClientManager(name)!= null)

        clientManagerList.getClientManager(name).sendEndGame(name, score, winner, scoreWinner);

    }

    /**
     * Method that checks the ping connection
     * @param name username of the connection
     */

    public void ping(String name){

        clientManagerList.getClientManager(name).ping();
    }

    /**
     * Method that stop the connection
     * @param name username of the connection
     */

    public void stop(String name){

        clientManagerList.getClientManager(name).stop();
    }

    public void end() {
        socketAcceptor.end();

        for (Object o : clientManagerList.getManagerHashMap().entrySet()) {

            Map.Entry couple = (Map.Entry) o;
            if (couple != null) {
                ClientManager c = (ClientManager) couple.getValue();
                c.stop();
            }
        }
        System.exit(0);
    }

}
