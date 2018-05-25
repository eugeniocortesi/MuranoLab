package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.rmi.RemoteException;
import java.util.Observable;

public class VirtualViewRMIImpl implements VirtualViewInt {

    private ViewInt concreteView;
    private Model model;

    public VirtualViewRMIImpl(ViewInt concreteView){
        this.concreteView = concreteView;

    }

    public void showLoginScreen() throws RemoteException {
        concreteView.showLoginScreen();
    }

    public void showLoggedScreen() throws RemoteException {
        //concreteView.showLoggedScreen();
    }

    public void showAlreadyLoggedScreen() {
        concreteView.showAlreadyLoggedScreen();
    }

    public void showTooManyUsersScreen() {
        concreteView.showTooManyUsersScreen();
    }

    public void showDisconnectScreen() {
        concreteView.showDisconnectScreen();
    }

    public void showAddedPlayer() {
        concreteView.showAddedPlayer();
    }

    public void showTurnInitialPhase() throws RemoteException {
        concreteView.showTurnInitialPhase();
    }

    public void showPlaceDie() throws RemoteException {
        concreteView.showPlaceDie();
    }

    public void showChooseCard() throws RemoteException {
        concreteView.showChooseCard();
    }

    public void showTurnEndPhase() throws RemoteException {
        concreteView.showTurnEndPhase();
    }

    public void showPoints() throws RemoteException {
        concreteView.showPoints();
    }

    public void update(Observable o, Object arg) {

       /* model = o;
        send(model);*/
    }
}
