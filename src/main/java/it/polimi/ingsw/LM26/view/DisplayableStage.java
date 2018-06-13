package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.view.images.ImageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayableStage {

    private Pane pane;
    private FXMLLoader fxmlLoader;

    public DisplayableStage(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(DisplayableStage.class.getResource(name));
        this.fxmlLoader=fxmlLoader;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public void show(Stage primaryStage){
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

}
