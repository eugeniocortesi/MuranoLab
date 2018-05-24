package it.polimi.ingsw.LM26.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayableStage {

    private Pane pane;

    public DisplayableStage(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(name));
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show(Stage primaryStage){
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
