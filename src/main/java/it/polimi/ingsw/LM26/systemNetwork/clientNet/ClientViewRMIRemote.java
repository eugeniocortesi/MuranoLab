package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientViewRMIRemote implements ClientViewRemote {

    ClientViewRMI clientViewRMI;

    public ClientViewRMIRemote(ClientViewRMI  clientViewRMI){
        this.clientViewRMI = clientViewRMI;
    }

    @Override
    public void connect() throws RemoteException {
        clientViewRMI.connect();
    }

    @Override
    public void requestedLogin() throws RemoteException {
        clientViewRMI.requestedLogin();
    }

    @Override
    public void logged(Boolean l, String name) throws RemoteException {
        clientViewRMI.logged(l,name);
    }

    @Override
    public void tooManyUsers() throws RemoteException {
        clientViewRMI.tooManyUsers();
    }

    @Override
    public void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) throws RemoteException {
        clientViewRMI.choseWindowPattern(user, id, windowDeck);
    }


}
