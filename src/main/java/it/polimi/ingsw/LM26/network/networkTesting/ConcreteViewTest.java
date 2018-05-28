package it.polimi.ingsw.LM26.network.networkTesting;

import it.polimi.ingsw.LM26.network.client.ClientRMIImpl;
import it.polimi.ingsw.LM26.network.client.ConnectionFromServer;
import it.polimi.ingsw.LM26.network.server.RMI.VirtualViewRMIImpl;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.io.*;

public class ConcreteViewTest implements ViewInt{

    private BufferedReader inKeyboard;
    private PrintWriter outVideo;
    private ClientRMIImpl clientRMI;


    public ConcreteViewTest(){
        inKeyboard = new BufferedReader(new InputStreamReader(System.in));
        outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);

        //from client to server
        clientRMI = new ClientRMIImpl(this);

    }

    public void start(){
        VirtualViewInt virtualViewRMI = new VirtualViewRMIImpl(this);
        clientRMI.connected(virtualViewRMI);
    }


    public void showLoginScreen() {
        outVideo.println (" Insert username for login ");
        try{
            String s1  = inKeyboard.readLine();
            clientRMI.login(s1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showLoggedScreen() {
        outVideo.println(" Logged with username inserted ");
    }


    public void showAlreadyLoggedScreen() {
        outVideo.println(" Already logged with this name ");
    }

    public void showTooManyUsersScreen() {
        outVideo.println(" Too many users logged, wait... ");
    }

    public void showDisconnectScreen() {
        outVideo.println(" You are not logged anymore! ");
    }

    public void showAddedPlayer() {
        outVideo.println(" Added new player ");
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

        c.start();
        c.showLoginScreen();
    }

}
