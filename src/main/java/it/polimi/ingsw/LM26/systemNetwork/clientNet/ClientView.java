package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;

import java.util.ArrayList;

public interface ClientView {

    void connect();

    void requestedLogin();

    void login ( String name );

    void logged ( Boolean l, String name );

    void tooManyUsers();

    void disconnect();

    void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    void chosenWindowPattern(ActionEventWindow actionEventWindow);

    void sendModel(Model m);

    void sendActionEventFromView(ActionEvent actionEvent);

    //TODO remove
    void sendAnswerFromController(String answer);
}
