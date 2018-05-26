package it.polimi.ingsw.LM26.network.client;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.rmi.RemoteException;
import java.util.Observable;

public class ConnectionFromServer implements VirtualViewInt {

    ViewInt view;

    public ConnectionFromServer(ViewInt view){
        this.view = view;
    }

    public void showLoginScreen() throws RemoteException {
        view.showLoginScreen();
    }

    public void showLoggedScreen() throws RemoteException {
        //view.showLoggedScreen();
    }

    public void showAlreadyLoggedScreen() throws RemoteException {
        view.showAlreadyLoggedScreen();
    }

    public void showTooManyUsersScreen() throws RemoteException {
        view.showTooManyUsersScreen();
    }

    public void showDisconnectScreen() throws RemoteException {
        view.showDisconnectScreen();
    }

    public void showAddedPlayer() throws RemoteException {
        view.showAddedPlayer();
    }

    public void showTurnInitialPhase() throws RemoteException {
        view.showTurnInitialPhase();
    }

    public void showPlaceDie() throws RemoteException {
        view.showPlaceDie();
    }

    public void showChooseCard() throws RemoteException {
        view.showChooseCard();
    }

    public void showTurnEndPhase() throws RemoteException {
        view.showTurnEndPhase();
    }

    public void showPoints() throws RemoteException {
        view.showPoints();
    }

    public void update(Observable o, Object arg) {
        //TODO
    }
}
