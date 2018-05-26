package it.polimi.ingsw.LM26.networkServer.ClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

public interface VirtualViewInt extends Remote, Observer {

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

    void update(Observable o, Object arg);
}
