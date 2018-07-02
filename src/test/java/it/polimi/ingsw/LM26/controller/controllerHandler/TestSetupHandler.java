package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import org.junit.Test;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestSetupHandler {

    Controller controller=new Controller();
    Model model=singletonModel();

    @Test
    public void checkAddPlayer(){

        controller.startServer();
        controller.getSetupHandler().setupPlayers("eugenio");
        controller.getSetupHandler().setupPlayers("claudia");
        controller.getSetupHandler().setupPlayers("tommaso");
        controller.getSetupHandler().setupPlayers("chiara");

    }

}