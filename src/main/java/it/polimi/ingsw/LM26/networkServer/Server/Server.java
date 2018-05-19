package it.polimi.ingsw.LM26.networkServer.Server;


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

    public ArrayList<ClientInt> adderConnectionsClient(ClientInt clientInt) {
        this.connectionsClient.add(clientInt);
        return connectionsClient;
    }

    public boolean removerConnectionsClient(ClientInt clientInt) {
        return this.connectionsClient.remove(clientInt);
    }

    public ConnectionAcepter getConnectionAcepter() {
        return connectionAcepter;
    }


    public Server(){

        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();
        connectionAcepter = new ConnectionAcepterSocket(this, dataServerConfiguration);
        connectionAcepter.acceptConnection();
        //TODO add RMI connection Acepter
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        new Server();

    }

}
