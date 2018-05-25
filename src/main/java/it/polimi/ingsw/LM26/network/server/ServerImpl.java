package it.polimi.ingsw.LM26.network.server;

import it.polimi.ingsw.LM26.network.server.RMI.ConnectionAcceptorRMIImpl;
import it.polimi.ingsw.LM26.network.server.socket.ClientHandlerSocketImpl;
import it.polimi.ingsw.LM26.network.server.socket.ConnectionAcceptorSocketImpl;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerImplementation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

//Implements configuration RMI and Socket
//Creates Connection Acceptor RMI and Socket
//Creates userConnections and socketConnections to store future ClientHandler

public class ServerImpl {


    //TODO add lobby

    private DataServerImplementation dataServerImplementation;
    //private HashMap<String, ClientHandlerInt> userConnections;
    private ConnectionToController userConnections;
    private ArrayList<ClientHandlerSocketImpl> socketConnections;
    private ConnectionAcceptorRMIImpl connectionAcceptorRMI;
    private ConnectionAcceptorSocketImpl connectionAcceptorSocket;

    public ServerImpl(){
        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);

        }catch(UnknownHostException he){
            he.printStackTrace();
        }
        //userConnections = new HashMap<String, ClientHandlerInt>();
        userConnections = new ConnectionToController(this);
        this.socketConnections = new ArrayList<ClientHandlerSocketImpl>();
        if(socketConnections == null) {
            System.err.println("socketConnection null");
        }
        connectionAcceptorRMI = new ConnectionAcceptorRMIImpl(this, dataServerConfiguration);

        connectionAcceptorSocket = new ConnectionAcceptorSocketImpl(this, dataServerConfiguration);


    }

    public ArrayList<ClientHandlerSocketImpl> getSocketconnections() {
        return socketConnections;
    }

    public ConnectionToController getUserConnections() {
        return userConnections;
    }

    /*
     public HashMap<String, ClientHandlerInt> getUserConnections() {
        return userConnections;
    }*/

    public ConnectionAcceptorRMIImpl getConnectionAcceptorRMI() {
        return connectionAcceptorRMI;
    }

    public void addSocketConnections(ClientHandlerSocketImpl clientHandler){
        if(clientHandler == null){
            System.out.println("clientHandlerInt" + clientHandler);
        }
        if (socketConnections == null){
            System.out.println(" socket connection problem");
        }
        this.socketConnections.add(clientHandler);
    }

    public boolean checkLogin(String username){
        /*if (!userConnections.isEmpty())
            for( User u : userConnections.keySet() ) {
                if (u.getName().equals(username))
                    return false;
            }
        return true;*/
        /*if (userConnections.get(username) == null ){
            return true;
        }
        return false;*/
        if (userConnections.getConnections(username))
            return false;
        return true;
    }

    public boolean checkNumberUser(){
        if(userConnections.size()<4)
            return true;
        else
            return false;
    }

    public void addUsername(String name, ClientHandlerInt clientHandlerInt){
        if(checkLogin(name)) {
            userConnections.add(name, clientHandlerInt);
        }
    }


}
