package it.polimi.ingsw.LM26.networkServer.Server;


import java.io.IOException;

public class Server {

    private ConnectionAcepter connectionAcepter;

    public ConnectionAcepter getConnectionAcepter() {
        return connectionAcepter;
    }

    public void setConnectionAcepter(ConnectionAcepter connectionAcepter) {
        this.connectionAcepter = connectionAcepter;
    }


    public Server(){
        connectionAcepter = new ConnectionAcepterSocket();
        connectionAcepter.acceptConnection();
    }

    //TODO remove
    public static void main(String[] args) throws IOException, InterruptedException
    {
        new Server();

    }

}
