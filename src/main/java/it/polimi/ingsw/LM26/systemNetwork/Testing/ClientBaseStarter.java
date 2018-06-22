package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientBase;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.view.cli.ConsoleStrings;


public class ClientBaseStarter {
    public static void main(String[] args) {


        ClientBase clientBase = new ClientBase();
        ViewInterface view = new ConsoleStrings(clientBase);
        view.showNetChoise();




    }
}
