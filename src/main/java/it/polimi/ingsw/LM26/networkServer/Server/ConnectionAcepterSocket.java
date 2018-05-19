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
    private static int SOCKETPORT;


    //private Socket socket;
    //TODO server da fare

    public ConnectionAcepterSocket(Server server, DataServerConfiguration dataServerConfiguration){
        this.server = server;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();
    }



    public void acceptConnection() {

        try {
            System.out.println("Acception Connection Socket");
            ServerSocket serversocket = new ServerSocket(SOCKETPORT);
            System.out.println("OK serversocket");
            while (true) {
                System.out.println("Server listening");
                Socket socket = serversocket.accept();
                ClientSocket clientSocket = new ClientSocket (socket, server);
                clientSocket.start();

                this.server.adderConnectionsClient(clientSocket);
                System.out.println("I'm adding " + server.getConnectionsClient().size() + " elements" );
            }
        }
        catch (IOException e) {
        }

    }


}
