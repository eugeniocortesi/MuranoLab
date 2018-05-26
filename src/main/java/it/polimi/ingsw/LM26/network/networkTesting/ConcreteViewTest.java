package it.polimi.ingsw.LM26.network.networkTesting;

import it.polimi.ingsw.LM26.network.client.ClientRMIImpl;
import it.polimi.ingsw.LM26.view.ViewInt;

public class ConcreteViewTest implements ViewInt{


    public void showLoginScreen() {
        System.out.println (" Insert username for login ");
    }

    public void showLoggedScreen() {
        System.out.println("Logged with username inserted");
    }

    @Override
    public void start() {

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
        ViewInt c = new ConcreteViewTest();
        //VirtualViewRMIImpl virtualViewRMI = new VirtualViewRMIImpl(c);

        //from client to server
        ClientRMIImpl concreteClientRMI = new ClientRMIImpl(c);
    }

}
