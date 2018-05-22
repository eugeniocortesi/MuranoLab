package it.polimi.ingsw.LM26.network.server;

import it.polimi.ingsw.LM26.network.server.RMI.ConnectionAcceptorRMIImpl;
import it.polimi.ingsw.LM26.network.server.socket.ClientHandlerSocketImpl;
import it.polimi.ingsw.LM26.network.server.socket.ConnectionAcceptorSocketImpl;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;
import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerImplementation;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerImpl {

    private DataServerImplementation dataServerImplementation;
    private HashMap<String, ClientHandlerInt> userConnections;
    private ArrayList<ClientHandlerSocketImpl> socketConnections;
    private ConnectionAcceptorRMIImpl connectionAcceptorRMI;
    private ConnectionAcceptorSocketImpl connectionAcceptorSocket;

    public ServerImpl(){
        dataServerImplementation = new DataServerImplementation();
        DataServerConfiguration dataServerConfiguration = dataServerImplementation.implementation();
        userConnections = new HashMap<String, ClientHandlerInt>();
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

    public HashMap<String, ClientHandlerInt> getUserConnections() {
        return userConnections;
    }

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
        return !userConnections.containsKey("username");
    }

    public boolean checkNumberUser(){
        if(userConnections.size()<4)
            return true;
        else
            return false;
    }

    public void addUsername(String name, ClientHandlerInt clientHandlerInt){
        userConnections.put(name, clientHandlerInt);
    }


}
