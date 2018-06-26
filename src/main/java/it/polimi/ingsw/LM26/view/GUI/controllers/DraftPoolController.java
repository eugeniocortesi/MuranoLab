package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class DraftPoolController {

    private ImageManager imageManager;
    private GameController gController;

    @FXML
    TilePane dPool;

    public void setMainController(GameController gController){
        this. gController=gController;
    }

    public void updateDPool(ImageManager imMa){
        imageManager=imMa;
        for(int i = 0; i< ModelManager.getModel().getDraftPool().getInDraft().size(); i++){
            ImageView dieImage=(ImageView) dPool.getChildren().get(i);
            imageManager.setDie(dieImage, ModelManager.getModel().getDraftPool().getInDraft().get(i));
        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<dPool.getChildren().size(); i++){
            dPool.getChildren().get(i).setDisable(disable);
        }
    }

}
