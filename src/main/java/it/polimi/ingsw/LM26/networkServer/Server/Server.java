package it.polimi.ingsw.LM26.networkServer.Server;


import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientInt;
import it.polimi.ingsw.LM26.networkServer.Server.ConnectionAcepter;
import it.polimi.ingsw.LM26.networkServer.Server.ConnectionAcepterSocket;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerImplementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

    private DataServerImplementation dataServerImplementation;

    private ConnectionAcepter connectionAcepter;

    private ArrayList<ClientInt> connectionsClient;

    private Model model;

    private Controller controller;

    public Server(){

        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();
        this.connectionsClient = new ArrayList<ClientInt>();

        this.model = new Model();
        //LOOK AT CONSTRUCTOR
        //AL CONTROLLER PASSERO' SOLO IL SERVER , PER CHIAMARE IL MODEL FARAI this.server.getModel();
        // this.controller = new Controller(this);

        connectionAcepter = new ConnectionAcepterSocket(this, dataServerConfiguration);
        connectionAcepter.acceptConnection();
        //TODO add RMI connection Acepter
    }


    public ArrayList<ClientInt> getConnectionsClient() {
        return connectionsClient;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

            this.getConnectionsClient().get(i).sendMessage(" Added new player in the game! ");
        }
    }

    public boolean removerConnectionsClient(ClientInt clientInt) {
        return this.connectionsClient.remove(clientInt);
    }

    public ConnectionAcepter getConnectionAcepter() {
        return connectionAcepter;
    }


    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        new Server();

    }

}
