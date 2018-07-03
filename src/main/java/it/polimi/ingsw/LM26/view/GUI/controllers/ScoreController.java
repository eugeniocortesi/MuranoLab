package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ScoreController {

    @FXML
    private VBox scoreTrack;

    private ImageManager imageManager;


    public void setUp(){
        imageManager= new ImageManager();
        Image imagebackg=imageManager.backgroundImage();
        BackgroundSize backgSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,true,true,true,false);

        BackgroundImage backgImage=new BackgroundImage(imagebackg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,backgSize);
        Background backg= new Background(backgImage);
        scoreTrack.setBackground(backg);
    }
}
