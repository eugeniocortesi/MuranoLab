package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        Model model = singletonModel();

        PlayerZone player1 = new PlayerZone("Eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        PlayerZone player3 = new PlayerZone( "Claudia", 2);
        PlayerZone player4 = new PlayerZone("Tommaso", 3);

        //ogni player sceglierà dalla view la windowpatterncard che vuole

        player1.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(0));
        player2.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(1));
        player3.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(2));
        player4.setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(3));

        player1.setNumberPlayer(0);
        player2.setNumberPlayer(1);
        player3.setNumberPlayer(2);
        player4.setNumberPlayer(3);

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        model.setPlayerList(playerList);

        model.getRoundTrackInt().getCurrentTurn();

        Controller controller = new Controller(model);
        




    }
}
