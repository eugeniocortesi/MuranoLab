package it.polimi.ingsw.LM26.networkServer.Server;


import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientInt;
import it.polimi.ingsw.LM26.networkServer.Server.ConnectionAcepter;
import it.polimi.ingsw.LM26.networkServer.Server.ConnectionAcepterSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

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

    public void setConnectionAcepter(ConnectionAcepter connectionAcepter) {
        this.connectionAcepter = connectionAcepter;
    }


    public Server(){
        connectionAcepter = new ConnectionAcepterSocket(this);
        connectionAcepter.acceptConnection();
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        new Server();

    }

}
