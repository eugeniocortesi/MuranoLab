package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.networkServer.serverConfiguration.DataServerConfiguration;


public class SocketAcceptor {

    private ServerBase myserver;
    private int SOCKETPORT;

    public SocketAcceptor(ServerBase serverBase, DataServerConfiguration dataServerConfiguration){
        myserver = serverBase;
        this.SOCKETPORT = dataServerConfiguration.getSOCKETPORT();
        accept();
    }

    public void accept(){

    }
}
