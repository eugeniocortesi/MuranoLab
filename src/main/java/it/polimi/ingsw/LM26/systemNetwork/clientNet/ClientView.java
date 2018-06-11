package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;

import java.util.ArrayList;

public abstract class ClientView extends ObservableSimple{

    public abstract void connect();

    public abstract void requestedLogin();

    public abstract void login ( String name );

    public abstract void logged ( Boolean l, String name );

    public abstract void tooManyUsers();

    public abstract void disconnect();

    public abstract void disconnected();

    public abstract void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    public abstract void chosenWindowPattern(ActionEventWindow actionEventWindow);

    public abstract void sendPrivateCard(ObjectivePrivateCard privateCard);

    public abstract void sendActionEventFromView(ActionEvent actionEvent);

    public abstract void sendAnswerFromController(String answer);

    public abstract void sendBeginTurn(String name, PlayerZone playerZone);

    public abstract void sendAddedPlayer(String field1);
}
