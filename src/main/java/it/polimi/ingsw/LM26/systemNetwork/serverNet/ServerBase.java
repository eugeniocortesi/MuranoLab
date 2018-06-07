package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.ServerController.*;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerImplementation;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI.RMIAcceptor;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket.SocketAcceptor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerBase extends ViewGameInterface {

    private Observer controller;
    private RMIAcceptor rmiAcceptor;
    private SocketAcceptor socketAcceptor;
    private ClientManagerList clientManagerList;
    private ArrayList<ClientManager> lobby;
    private boolean playing;

    private Receiver receiver;
    private MessageQueue queueController;
    private VisitorInt visitor;

    private DataServerImplementation dataServerImplementation;
    private DataServerConfiguration dataServerConfiguration;

    public ServerBase(Observer controllerInt){


        controller = controllerInt;
        playing = false;


        lobby = new ArrayList<ClientManager>();
        dataServerImplementation = new DataServerImplementation();
        dataServerConfiguration = dataServerImplementation.implementation();

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

    public void startAcceptor(){
        rmiAcceptor = new RMIAcceptor(this, dataServerConfiguration);
        socketAcceptor = new SocketAcceptor(this, dataServerConfiguration);
        socketAcceptor.start();
        receiver.getVisitorInt().getObservable().register(controller);
        System.out.println("Registered Observer");
        receiver.start();
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
        if(checkNumberUsers()){

            //clientManager.setObservable(receiver);
            return clientManagerList.addClientManager(s, clientManager);

        }
        return false;
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
        if (clientManagerListSize()<4)
            return true;
        //TODO change it
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

    public void checkPlayers(){
        clientManagerList.checkNumberLogged();
    }


    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        System.out.println("called");
        clientManagerList.getClientManager(user).choseWindowPattern(user, id, windowDeck);

    }
}
