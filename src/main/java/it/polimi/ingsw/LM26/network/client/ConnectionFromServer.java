package it.polimi.ingsw.LM26.network.client;

import it.polimi.ingsw.LM26.network.server.RMI.ClientHandlerRMIRemoteImpl;
import it.polimi.ingsw.LM26.network.server.RMI.ClientHandlerRMIRemoteInt;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualView;
import it.polimi.ingsw.LM26.networkServer.ClientHandler.VirtualViewInt;
import it.polimi.ingsw.LM26.view.ViewInt;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class ConnectionFromServer implements VirtualViewInt {

    ViewInt view;

    public ConnectionFromServer(ViewInt view, Registry registry, int PORT){
        this.view = view;
        try {
            VirtualViewInt skeleton = (VirtualViewInt) UnicastRemoteObject.exportObject(this, PORT);
            registry.bind("client", skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
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

    @Override
    public void start() throws RemoteException {
        view.start();
    }

}
