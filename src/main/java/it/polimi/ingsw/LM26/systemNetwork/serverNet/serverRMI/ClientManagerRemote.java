package it.polimi.ingsw.LM26.systemNetwork.serverNet.serverRMI;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientManagerRemote extends Remote {

    void connect() throws  RemoteException;

    int getAvailableId() throws RemoteException;

    void login(String name) throws RemoteException;

    void disconnect() throws RemoteException;

    void chosenWindowPattern(ActionEventWindow actionEventWindow) throws RemoteException;

    void sendActionEventFromView(ActionEvent actionEvent) throws RemoteException;
}
