package it.polimi.ingsw.LM26.networkServer.Server;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientInt;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientSocket;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ConnectionAcepterSocket implements ConnectionAcepter {

    Server server;

    private ServerSocket serverSocket;
    private  Vector<ClientInt> connections;
    private static int SOCKETPORT;

    public Vector<ClientInt> getConnections() {
        return connections;
    }

    public void setConnections(Vector<ClientInt> connections) {
        this.connections = connections;
    }



    //private Socket socket;
    //TODO server da fare

    public ConnectionAcepterSocket(Server server, DataServerConfiguration dataServerConfiguration){
        connections = new Vector<ClientInt>();
        this.server = server;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();
    }



    public void acceptConnection() {

        try {
            System.out.println("Acception Connection Socket");
            ServerSocket serversocket = new ServerSocket(SOCKETPORT);
            System.out.println("Istanziata serversocket");
            while (true) {
                System.out.println("Sono nel ciclo");
                Socket socket = serversocket.accept();
                ClientSocket clientSocket = new ClientSocket (socket);
                clientSocket.start();

                this.connections.add(clientSocket);
                System.out.println("Sto aggiungendo" + connections.size() + "elemento" );
            }
        }
        catch (IOException e) {
        }

    }

    public void notifyClientAddedNewPlayer(){

        for( int i = 0; i< connections.size(); i++){

            connections.get(i).sendMessage("Added new player in the game! ");
        }
    }
}
