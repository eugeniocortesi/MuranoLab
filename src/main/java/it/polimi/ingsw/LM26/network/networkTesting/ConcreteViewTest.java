package it.polimi.ingsw.LM26.network.networkTesting;

import it.polimi.ingsw.LM26.network.client.ConcreteClientRMIImpl;
import it.polimi.ingsw.LM26.network.server.RMI.VirtualViewRMIImpl;
import it.polimi.ingsw.LM26.view.ViewInt;

public class ConcreteViewTest implements ViewInt{


    public void showLoginScreen() {
        System.out.println (" Insert username for login ");

    }

    public void showAlreadyLoggedScreen() {
        System.out.println(" Already logged with this name ");
    }

    public void showTooManyUsersScreen() {
        System.out.println(" Too many users logged, wait... ");
    }

    public void showDisconnectScreen() {
        System.out.println(" You are not logged anymore! ");
    }

    public void showAddedPlayer() {
        System.out.println(" Added new player ");
    }

    public void showTurnInitialPhase() {

    }

    public void showPlaceDie() {

    }

    public void showChooseCard() {

    }

    public void showTurnEndPhase() {

    }

    public void showPoints() {

    }

    public static void main(String[] args){


        //from server to client
        ConcreteViewTest c = new ConcreteViewTest();
        VirtualViewRMIImpl virtualViewRMI = new VirtualViewRMIImpl(c);

        //from client to server
        ConcreteClientRMIImpl concreteClientRMI = new ConcreteClientRMIImpl();
    }

}
