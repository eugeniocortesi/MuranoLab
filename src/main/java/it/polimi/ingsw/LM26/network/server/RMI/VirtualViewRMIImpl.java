package it.polimi.ingsw.LM26.network.server.RMI;

import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.rmi.RemoteException;

public class VirtualViewRMIImpl implements VirtualViewInt {

    private ViewInt concreteView;

    public VirtualViewRMIImpl(ViewInt concreteView){
        this.concreteView = concreteView;

    }

    public void showLoginScreen() throws RemoteException {
        concreteView.showLoginScreen();
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
}
