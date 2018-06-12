package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.view.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameController {

    private ImageManager imageManager;

    @FXML
    private ImageView publicCard1;

    public void setPublicCard1(){
        Image i=imageManager.getObjectiveCard(13);
        publicCard1.setImage(i);
    }

}
