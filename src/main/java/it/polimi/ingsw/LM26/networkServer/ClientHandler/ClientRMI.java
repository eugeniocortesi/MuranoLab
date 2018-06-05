package it.polimi.ingsw.LM26.networkServer.ClientHandler;

import it.polimi.ingsw.LM26.networkServer.Client.ClientImplementationRMI;

public class ClientRMI implements ClientInt {

    //scrittura su RMI bloccante per il client che chiede al server
    private boolean logged;
    private String user;
    private int id;
    private VirtualViewInt virtualView;

    public boolean isLogged() {
        return false;
    }

    public void setLogged(boolean logged) {

    }

    public void disconnect() {
        ;
    }

    public void login() {
        //virtualView.showLoginScreen();
    }

    public void login(String username, ClientImplementationRMI clientImplRMI) {

        //TODO cerca id, color

    }


    public void run() {

        /*try{

        }catch(RemoteException){

        }*/
    }

    public String getUsername() {
        return this.user;
    }

    public void start() {
        run();
    }
}
