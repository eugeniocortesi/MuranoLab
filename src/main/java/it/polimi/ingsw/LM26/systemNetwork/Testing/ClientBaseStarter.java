package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientBaseStarter {
    public static void main(String[] args) {

        //Logger.getLogger(ServerBase.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

        ClientBase clientBase = new ClientBase();
        ViewInterface view = new ConsoleStrings(clientBase);
        view.showNetChoise();




    }
}
