package it.polimi.ingsw.LM26.systemNetwork.clientNet;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;

import java.util.ArrayList;

public interface ClientView {

    void connect();

    void requestedLogin();

    void login ( String name );

    void logged ( Boolean l, String name );

    void tooManyUsers();

    void disconnect();

    void choseWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck);

    void chosenWindowPattern(String user, WindowPatternCard windowcard);
}
