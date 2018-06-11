package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

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

    @Override
    public void sendModel(Model m) {
        clientViewRMI.notify(m);
    }

    @Override
    public void sendAnswerFromController(String answer) throws RemoteException {
        clientViewRMI.sendAnswerFromController(answer);
    }

    @Override
    public void sendPrivateCard(ObjectivePrivateCard privateCard) throws RemoteException {
        clientViewRMI.sendPrivateCard(privateCard);
    }

    @Override
    public void sendBeginTurnMessage(String name, PlayerZone playerZone) throws RemoteException {
        clientViewRMI.sendBeginTurn(name, playerZone);
    }

    @Override
    public void disconnected() throws RemoteException {
        clientViewRMI.disconnected();
    }


}
