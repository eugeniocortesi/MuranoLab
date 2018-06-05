package it.polimi.ingsw.LM26.networkServer.ClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VirtualViewInt extends Remote {

    void showLoginScreen() throws RemoteException;

    void showLoggedScreen() throws RemoteException;

    void showAlreadyLoggedScreen() throws RemoteException;

    void showTooManyUsersScreen() throws RemoteException;

    void showDisconnectScreen() throws RemoteException;

    void showAddedPlayer() throws RemoteException;

    void showTurnInitialPhase() throws RemoteException;

    void showPlaceDie() throws RemoteException;

    void showChooseCard() throws RemoteException;

    void showTurnEndPhase() throws RemoteException;

    void showPoints() throws RemoteException;

    void start() throws RemoteException;
}
