package it.polimi.ingsw.LM26.networkServer.ClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VirtualViewInt extends Remote {

    void showLoginScreen() throws RemoteException;

    void showTurnInitialPhase() throws RemoteException;

    void showPlaceDie() throws RemoteException;

    void showChooseCard() throws RemoteException;

    void showTurnEndPhase() throws RemoteException;

    void showPoints() throws RemoteException;
}
