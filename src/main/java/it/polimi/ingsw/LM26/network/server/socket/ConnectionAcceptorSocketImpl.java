package it.polimi.ingsw.LM26.network.server.socket;

import it.polimi.ingsw.LM26.network.server.ServerImpl;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.ClientSocket;
import it.polimi.ingsw.LM26.networkServer.Server.Server;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Creates new ConnectionAcceptor Socket and accepts new socket connections
// Creates a new ClientHandler for each connection
// Adds every connections to server ( in socketConnections and userConnection )

public class ConnectionAcceptorSocketImpl {

    ServerImpl server;

    private ServerSocket serverSocket;
    private int SOCKETPORT;

    public ConnectionAcceptorSocketImpl (ServerImpl server, DataServerConfiguration dataServerConfiguration){
        this.server = server;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();
        this.acceptConnection();
    }



    public void acceptConnection() {

        try {
            System.out.println("Acception Connection Socket");
            ServerSocket serversocket = new ServerSocket(SOCKETPORT);
            System.out.println("OK serversocket");
            while (true) {
                System.out.println("Server listening");
                Socket socket = serversocket.accept();
                ClientHandlerSocketImpl clientHandlerSocket = new ClientHandlerSocketImpl (socket, server);
                //System.out.println("Created");
                clientHandlerSocket.start();
                //System.out.println("Started");
                if(clientHandlerSocket == null){
                    System.out.println("AHIA");
                }
                this.server.addSocketConnections(clientHandlerSocket);
                System.out.println("I'm adding " + server.getSocketconnections().size() + " socket elements" );
            }
        }
        catch (IOException e) {
        }

    }

}
