package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class DraftPoolController {

    private ImageManager imageManager;
    private GameController gController;
    private DieInt die;

    @FXML
    TilePane dPool;

    public void setUpDPool(GameController gController){
        this.gController=gController;
    }

    public void updateDPool(ImageManager imMa){
        imageManager=imMa;
        for(int i = 0; i< ModelManager.getModel().getDraftPool().getInDraft().size(); i++){
            ImageView dieImage=(ImageView) dPool.getChildren().get(i);
            imageManager.setDie(dieImage, ModelManager.getModel().getDraftPool().getInDraft().get(i));
            int d=i;
            dPool.getChildren().get(i).setOnMouseClicked((MouseEvent event)->handleDiceClicked(d));
        }
        for(int i= ModelManager.getModel().getDraftPool().getInDraft().size(); i<dPool.getChildren().size(); i++){
            ImageView emptyCell=(ImageView) dPool.getChildren().get(i);
            try{
                emptyCell.setImage(null);
            }catch (RuntimeException e){ }
        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<dPool.getChildren().size(); i++){
            dPool.getChildren().get(i).setDisable(disable);
        }
    }

    private void handleDiceClicked(int idx){
        die=ModelManager.getModel().getDraftPool().getInDraft().get(idx);
        gController.setMoveLabel("Selezionato dado numero "+(idx+1));
        gController.sendDPoolEvent(die);
    }
}
