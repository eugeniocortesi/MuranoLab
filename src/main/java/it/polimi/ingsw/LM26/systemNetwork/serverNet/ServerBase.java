package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.ServerController.Observer;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.SingletonModel;
import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private DataServerImplementation dataServerImplementation;

    public ServerBase(Observer controllerInt){

        controller = controllerInt;
        playing = false;

        lobby = new ArrayList<ClientManager>();
        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();

        System.out.println("SocketPort " +dataServerConfiguration.getSOCKETPORT()+ " ClientRMI " + dataServerConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataServerConfiguration.getServerRMIPORT());

        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);

        }catch(UnknownHostException he){
            he.printStackTrace();
        }

        clientManagerList = new ClientManagerList(this);

        rmiAcceptor = new RMIAcceptor(this, dataServerConfiguration);
        socketAcceptor = new SocketAcceptor(this, dataServerConfiguration);

    }

    public Observer getController() {
        return controller;
    }

    public synchronized boolean addView(String s, ClientManager clientManager){
        if(checkNumberUsers())
            return clientManagerList.addClientManager(s, clientManager);
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
        else if (!playing) {
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
        }
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
