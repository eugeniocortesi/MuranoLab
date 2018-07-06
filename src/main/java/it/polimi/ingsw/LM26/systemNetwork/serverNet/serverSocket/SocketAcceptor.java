package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverSocket;

import it.polimi.ingsw.LM26.fileConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SocketAcceptor class
 * @author Chiara Criscuolo
 * It manages all the new socket Connections and created a ClientManagerSocket for each one
 */

public class SocketAcceptor extends Thread{

    private ServerBase serverBase;

    private int SOCKETPORT;

    private boolean inAction;

    private static final Logger LOGGER = Logger.getLogger(SocketAcceptor.class.getName());

    /**
     * Constructor
     * @param serverBase reference to the Server
     * @param dataServerConfiguration contains data from file of configuration of the Server
     */

    public SocketAcceptor(ServerBase serverBase, DataServerConfiguration dataServerConfiguration){
        this.serverBase = serverBase;

        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();

        this.inAction= true;

    }

    /**
     * Method that accept socket connections
     */
    @Override
    public void run(){

        ServerSocket serversocket = null;

        try {

            serversocket = new ServerSocket(SOCKETPORT);

            LOGGER.log(Level.SEVERE,"Server socket created");

            while (inAction) {

                Socket socket = serversocket.accept();

                ClientManager clientSocket = new ClientManagerSocket(socket, serverBase);

                Thread t = new Thread(clientSocket);

                t.start();

            }
        }
        catch (IOException e) {

            System.err.println("Impossible to create socket, reset Server");
        }
        finally {

            try {
                if(serversocket != null)
                    serversocket.close();

            } catch (IOException e) {

                System.err.println("Impossible to close Server socket");
            }
        }

    }

    public void end(){
        inAction = false;
    }
}
