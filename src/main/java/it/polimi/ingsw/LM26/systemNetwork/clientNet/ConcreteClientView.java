package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConcreteClientView extends ViewInterface {

    private ClientView clientView;
    private BufferedReader inKeyboard;

    private DataClientImplementation dataClientImplementation;
    private DataClientConfiguration dataClientConfiguration;

    public ConcreteClientView(){

        inKeyboard = new BufferedReader(new InputStreamReader(System.in));
        dataClientImplementation = new DataClientImplementation();
        dataClientConfiguration = dataClientImplementation.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()+ " ClientRMI " + dataClientConfiguration.getClientRMIPORT()
        + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());
        showNetChoise();
    }

    @Override
    public void showNetChoise() {
        System.out.println(" How do you want to connect to Server? R for RMI , S for Socket");

        try {
            String answer = inKeyboard.readLine();
            if (answer.equals("R"))
                clientView = new ClientViewRMI(this, dataClientConfiguration);
            else if (answer.equals("S"))
                clientView = new ClientViewSocket(this, dataClientConfiguration);
           clientView.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showLoginScreen() {
        System.out.println("Insert username to login: ");
        try {
            String name = inKeyboard.readLine();
            clientView.login(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showLoggedScreen() {
        System.out.println("You logged successfully!");
    }

    @Override
    public void showAlreadyLoggedScreen() {
        System.out.println(" The username was already token ");
        showLoginScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        System.out.println("Too many users connected, you have to wait the end of the previous game to play");
    }
}
