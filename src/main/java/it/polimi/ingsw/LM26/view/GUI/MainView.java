package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.Client;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.reloadDecks.singletonDecks;

public class MainView extends Application {

    private View view;
    private ClientInt clientInt = new Client();

    private Decks deck=singletonDecks();
    private ArrayList<WindowPatternCard> testarray = new ArrayList<WindowPatternCard>();

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        view = new View(primaryStage, clientInt);
        /*for(int i=0; i<4; i++){
            testarray.add(deck.getWindowPatternCardDeck().get(i));
        }
        view.showWindowPattern("we", 1, testarray);*/

        view.showCurrentMenu("name");


        //view.showCentralPhaseScreen();
    }
}
