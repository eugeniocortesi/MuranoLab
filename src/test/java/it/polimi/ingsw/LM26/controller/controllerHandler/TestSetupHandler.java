package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import org.junit.Test;

public class TestSetupHandler {

    private Controller controller=new Controller();

    @Test
    public void checkAddPlayer(){

        controller.startServer();
        controller.getSetupHandler().setupPlayers("eugenio");
        controller.getSetupHandler().setupPlayers("claudia");
        controller.getSetupHandler().setupPlayers("tommaso");
        controller.getSetupHandler().setupPlayers("chiara");

    }

}