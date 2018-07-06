package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.systemNetwork.Testing.ClientBaseStarter;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Logger.getLogger(ServerBase.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

        Logger.getLogger(ClientBaseStarter.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

        Logger.getLogger(Controller.class.getPackage().getName()).getParent().getHandlers()[0].setLevel(Level.OFF);

        Controller controller = new Controller();

        controller.startServer();
    }
}
