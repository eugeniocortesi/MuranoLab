package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.network.server.ClientHandlerInt;
import it.polimi.ingsw.LM26.network.server.ServerImpl;

public class ClientHandlerRMIImpl implements ClientHandlerInt {

    private ServerImpl myserver;

    public ClientHandlerRMIImpl(ServerImpl server){
        this.myserver = server;
    }

    public void login(String username) {

        if(this.myserver.checkLogin(username) && this.myserver.checkNumberUser()){
            System.out.println(username + " logged");
            myserver.addUsername(username, this);
            System.out.println ("I'm adding " + myserver.getUserConnections().size() + " element");
            //TODO notify other players

        }
        else{
            System.out.println(username + " not logged");
        }
    }

    public void disconnect(String username) {

    }
}
