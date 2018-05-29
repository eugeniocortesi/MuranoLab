package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketAcceptor {

    private ServerBase myserver;
    private int SOCKETPORT;

    public SocketAcceptor(ServerBase serverBase, DataServerConfiguration dataServerConfiguration){
        myserver = serverBase;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();
        accept();
    }

    public void accept(){

        try {
            System.out.println("Acception Connection Socket");
            ServerSocket serversocket = new ServerSocket(SOCKETPORT);
            System.out.println("OK serversocket");
            while (true) {
                System.out.println("Server listening");
                Socket socket = serversocket.accept();
                ClientManager clientSocket = new ClientManagerSocket (socket, myserver);
                /*myserver.addClientManager(clientSocket);
                System.out.println("I'm adding " + myserver.lobbySize() + " elements" );*/

                clientSocket.start();

            }
        }
        catch (IOException e) {
        }
    }
}
