package it.polimi.ingsw.LM26.networkServer.Server;


import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientInt;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.networkServer.Server.ConnectionAcepterSocket;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerImplementation;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

    private DataServerImplementation dataServerImplementation;

    private ConnectionAcepterSocket connectionAcepterSocket;

    private ConnectionAcepterRMI connectionAcepterRMI;

    private ArrayList<ClientInt> connectionsClient;

    private ViewInt view;

    private VirtualControllerInt virtualController;

    /*private Model model;

    private Controller controller;*/

    public Server(){



        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();

        this.connectionsClient = new ArrayList<ClientInt>();
        this.view = new ViewList();
        this.virtualController = new VirtualController();

        //this.model = new Model();

        //LOOK AT CONSTRUCTOR
        //AL CONTROLLER PASSERO' SOLO IL SERVER , PER CHIAMARE IL MODEL FARAI this.server.getModel();
        // this.controller = new Controller(this);

        connectionAcepterSocket = new ConnectionAcepterSocket(this, dataServerConfiguration);
        connectionAcepterSocket.acceptConnection();

        connectionAcepterRMI = new ConnectionAcepterRMI(this, dataServerConfiguration);

    }


    public ArrayList<ClientInt> getConnectionsClient() {
        return connectionsClient;
    }

    public ArrayList<ClientInt> adderConnectionsClient(ClientInt clientInt) {
        this.connectionsClient.add(clientInt);

        //TODO manage the reception of the message by notifyClientAddedPlayer
        //notifyClientAddedNewPlayer();
        return connectionsClient;
    }

    public boolean checkUsername(String username){

        for( int i = 0; i< this.getConnectionsClient().size(); i++){

            if(this.getConnectionsClient().get(i).getUsername().equals(username))
                    return false;
        }

        return true;

    }

    public void notifyClientAddedNewPlayer(){

        for( int i = 0; i< this.getConnectionsClient().size()-1; i++){

           // this.getConnectionsClient().get(i).sendMessage(" Added new player in the game! ");
        }
    }

    public boolean removerConnectionsClient(ClientInt clientInt) {
        return this.connectionsClient.remove(clientInt);
    }

    public ConnectionAcepterSocket getConnectionAcepterSocket() {
        return connectionAcepterSocket;
    }

    public ConnectionAcepterRMI getConnectionAcepterRMI() {
        return connectionAcepterRMI;
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        new Server();

    }

}
