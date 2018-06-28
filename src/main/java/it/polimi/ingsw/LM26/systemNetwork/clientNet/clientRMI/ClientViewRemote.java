package it.polimi.ingsw.LM26.systemNetwork.clientNet.clientRMI;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientViewRemote extends Remote {
    void connect() throws RemoteException;

    void requestedLogin () throws RemoteException;

    void logged (Boolean l, String name) throws RemoteException;

    void tooManyUsers() throws RemoteException;

    void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) throws RemoteException;

    void sendModel(Model m) throws RemoteException;

    void sendAnswerFromController(String answer) throws RemoteException;

    void sendPrivateCard(ObjectivePrivateCard privateCard) throws RemoteException;

    void sendBeginTurnMessage(String name, PlayerZone playerZone) throws RemoteException;

    void disconnected() throws RemoteException;

    void sendAddedPlayer(String name) throws RemoteException;

    void sendCurrentMenu(String name) throws RemoteException;

    void pong() throws RemoteException;
}
