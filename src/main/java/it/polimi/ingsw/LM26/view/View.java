package it.polimi.ingsw.LM26.view;

import javafx.application.Platform;
import javafx.stage.Stage;

public class View implements ViewInt{
    private DisplayableStage displayableStage1 = new DisplayableStage("LogIn.fxml");
    //private DisplayableStage displayableStage2 = new DisplayableStage(".fxml");
    private Stage stage;

    public View(Stage stage) {
        this.stage = stage;
    }

    public void showLoginScreen() {
       Platform.runLater(new Runnable() {
           public void run() {
               displayableStage1.show(stage);
           }
       });
    }

    public void showTooManyUsersScreen() {

    }

    public void showDisconnectScreen() {

    }

    public void showAddedPlayer() {

    }

    public void showTurnInitialPhase() {

    }

    public void showPlaceDie() {

    }

    public void showChooseCard() {

    }

    public void showTurnEndPhase() {

    }

    public void showPoints() {

    }
}
