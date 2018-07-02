package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.*;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.systemNetwork.netConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.RMIAcceptor;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket.SocketAcceptor;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerGame;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerImplementation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ServerBase extends ViewGameInterface {

    private ObservableSimple model;
    private ObserverSimple observerSimple;
    private Observer controller;
    private RMIAcceptor rmiAcceptor;
    private SocketAcceptor socketAcceptor;
    private ClientManagerList clientManagerList;
    private ArrayList<ClientManager> lobby;

    private Receiver receiver;
    private MessageQueue queueController;
    private VisitorInt visitor;

    private DataServerConfiguration dataServerConfiguration;
    private TimerImplementation timerImplementation;
    private TimerConfiguration timerConfiguration;

    private boolean gameIsGoing;

    TimerGame timerGame;

    public ServerBase(Observer controllerInt){


        controller = controllerInt;
        gameIsGoing = false;


        lobby = new ArrayList<ClientManager>();
        dataServerConfiguration = new DataServerConfiguration().implementation();
        timerImplementation = new TimerImplementation();
        timerConfiguration = timerImplementation.implentation();

        System.out.println("SocketPort " +dataServerConfiguration.getSOCKETPORT()+ " ClientRMI " + dataServerConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataServerConfiguration.getServerRMIPORT());

        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);

        }catch(UnknownHostException he){
            he.printStackTrace();
        }

        clientManagerList = new ClientManagerList(this);


        queueController = new MessageQueue();
        visitor = new VisitorMessage();
        receiver = new Receiver(queueController, visitor);

    }

    public void startAcceptor(Observer controller, ObservableSimple model){
        rmiAcceptor = new RMIAcceptor(this, dataServerConfiguration);
        socketAcceptor = new SocketAcceptor(this, dataServerConfiguration);
        socketAcceptor.start();
        receiver.getVisitorInt().getObservable().register(controller);
        System.out.println("Registered Observer");
        this.model = model;
        receiver.start();
        System.out.println("Timer start!");

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

    public Receiver getReceiver() {
        return receiver;
    }

    public MessageQueue getQueueController() {
        return queueController;
    }

    public Observer getController() {
        return controller;
    }

    public synchronized boolean addView(String s, ClientManager clientManager){
        if(gameIsGoing)
            return false;
        if(checkNumberUsers()){
            updatePlayers(s);

            boolean b = clientManagerList.addClientManager(s, clientManager);
            if(clientManagerListSize()== 2){
                System.out.println("Timer start!");
                timerGame = new TimerGame(this, timerConfiguration.getTimerEnd());
                timerGame.scheduleTimerPlayer();
            }
            return b;

        }
        return false;
    }

    private synchronized void updatePlayers(String s){

        Iterator iterator = clientManagerList.getManagerHashMap().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry couple = (Map.Entry)iterator.next();
            System.out.println(couple.getKey());

            if(couple.getValue()!= null){
                ClientManager cm = (ClientManager) couple.getValue();
                cm.sendAddedPlayer(s);
            }
            else{
                System.out.println("Client manager null");
            }

            System.out.println("Updated players");
        }
    }

    public synchronized void addClientManager(ClientManager clientManager){
        lobby.add(clientManager);
    }

    public int lobbySize(){
        return lobby.size();
    }

    public int clientManagerListSize(){
        return clientManagerList.size();
    }

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

    public boolean isGameGoing(){
        return gameIsGoing;
    }

    public void registerModel(){

        Iterator iterator = clientManagerList.getManagerHashMap().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry couple = (Map.Entry)iterator.next();
            System.out.println(couple.getKey());

            if (model == null) System.out.println("Model null");
            if((ObserverSimple) couple.getValue()!= null){
                model.register((ObserverSimple) couple.getValue());
            }
            else{
                System.out.println("Client manager null");
            }
        }
    }

    public void checkPlayers(){
        clientManagerList.checkNumberLogged();
    }


    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        System.out.println("called windows");
        clientManagerList.getClientManager(user).choseWindowPattern(user, id, windowDeck);

    }

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {
        System.out.println("called private cards");
        clientManagerList.getClientManager(name).sendPrivateCard(privateCard);
    }

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {

        System.out.println("called set player menu");
        clientManagerList.getClientManager(name).sendBeginTurnMessage(name, player);
    }

    @Override
    public void showCurrentMenu(String name) {

        System.out.println("called show current menu");
        clientManagerList.getClientManager(name).sendCurrentMenu(name);
    }

    @Override
    public void showAnswerFromController(String name, String answer) {
        System.out.println("called show current menu");
        clientManagerList.getClientManager(name).sendAnswerFromController(answer);
    }

    @Override
    public void showEndGame(String name, Object score) {

        System.out.println("called show current menu");
        clientManagerList.getClientManager(name).sendEndGame(score);
    }

    public void ping(String name){

        clientManagerList.getClientManager(name).ping();
    }

    public void stop(String name){

        clientManagerList.getClientManager(name).stop();
    }
}
