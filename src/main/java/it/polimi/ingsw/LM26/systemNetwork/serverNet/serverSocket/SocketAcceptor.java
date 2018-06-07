package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.systemNetwork.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketAcceptor extends Thread{

    private ServerBase myserver;
    private int SOCKETPORT;

    private static final Logger LOGGER = Logger.getLogger(SocketAcceptor.class.getName());


    public SocketAcceptor(ServerBase serverBase, DataServerConfiguration dataServerConfiguration){
        myserver = serverBase;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();

    }

    public void accept(){

        try {
            LOGGER.log(Level.WARNING,"Acception Connection Socket");
            ServerSocket serversocket = new ServerSocket(SOCKETPORT);
            LOGGER.log(Level.SEVERE,"serversocket created");
            while (true) {
                LOGGER.log(Level.SEVERE,"Server listening");
                Socket socket = serversocket.accept();
                ClientManager clientSocket = new ClientManagerSocket(socket, myserver);
                Thread t = new Thread(clientSocket);

                t.start();
                //clientSocket.start();

            }
        }
        catch (IOException e) {
        }
    }

    public void run(){
        accept();
    }
}
