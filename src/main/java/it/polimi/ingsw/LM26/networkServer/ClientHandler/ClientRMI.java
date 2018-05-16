package it.polimi.ingsw.LM26.networkServer.ClientHandler;

import java.rmi.RemoteException;

public class ClientRMI implements ClientInt {

    //scrittura su RMI bloccante per il client che chiede al server
    private boolean logged;
    private String user;
    private int id;

    public boolean isLogged() {
        return false;
    }

    public void setLogged(boolean logged) {

    }

    public void sendMessage(String message) {

    }

    public String receiveMessage() {
        return null;
    }

    public void disconnect() {
        ;
    }

    public void login() {
        ;
    }

    public void login(String username) {

        //TODO cerca id, color

    }


    public void run() {

        /*try{

        }catch(RemoteException){

        }*/
    }

    public void start() {
        run();
    }
}
