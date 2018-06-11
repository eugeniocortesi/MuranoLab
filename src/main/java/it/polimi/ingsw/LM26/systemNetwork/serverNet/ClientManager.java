package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;

import java.util.ArrayList;

public abstract class ClientManager implements Runnable, ObserverSimple {

    public abstract void connect();

    public abstract int getAvailableId();

    public abstract void requestedLogin();

    public abstract void login(String name);

    public abstract void logged(Boolean l, String name);

    public abstract void disconnected();

    public abstract void disconnect(String s );

    public abstract void run();

    public abstract void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    public abstract void chosenWindowPattern(ActionEventWindow actionEventWindow);

    public abstract void sendPrivateCard(ObjectivePrivateCard card);

    public abstract void sendModel(Model m);

    public abstract void sendActionEventFromView(ActionEvent actionEvent);

    public abstract void sendAnswerFromController(String message);

    public abstract void sendBeginTurnMessage(String name, PlayerZone playerZone);

}
