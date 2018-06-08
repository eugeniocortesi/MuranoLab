package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientViewRemote extends Remote {
    void connect() throws RemoteException;

    void requestedLogin () throws RemoteException;

    void logged (Boolean l, String name) throws RemoteException;

    void tooManyUsers() throws RemoteException;

    void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) throws RemoteException;

    void sendModel(Model m);
}
