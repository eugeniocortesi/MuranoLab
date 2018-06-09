package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class MainView extends Application {

    private View view;

    private Decks deck=singletonDecks();
    private ArrayList<WindowPatternCard> testarray = new ArrayList<WindowPatternCard>();

    public static void main(String[] args){

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        view = new View(primaryStage);
        for(int i=0; i<4; i++){
            testarray.add(deck.getWindowPatternCardDeck().get(i));
        }
        view.showWindowPattern("we", 1, testarray);


        //view.showCentralPhaseScreen();
    }
}
