package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.network.networkTesting.ConcreteViewTest;
import it.polimi.ingsw.LM26.view.MainView;
import it.polimi.ingsw.LM26.view.View;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        //ViewInt view = new ConcreteViewTest();
        // Server server = new Server();
        // view = server.getView();

        Controller controller = new Controller();
        




    }
}
