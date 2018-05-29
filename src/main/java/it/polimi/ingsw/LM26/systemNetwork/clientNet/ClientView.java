package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.controller.ActionEvent;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

public interface ClientView {

    void connect();

    void requestedLogin();

    void login ( String name );

    void logged ( Boolean l, String name );

    void tooManyUsers();

    void disconnect();

    void chosedWindow(WindowPatternCard windowPatternCard);

    void placedDie(ActionEvent actionEvent);
}
