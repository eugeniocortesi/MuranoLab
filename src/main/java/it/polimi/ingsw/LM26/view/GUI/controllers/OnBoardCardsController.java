package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class OnBoardCardsController {

    private ImageManager imageManager;

    @FXML
    private TilePane cards;

    public void setUpCards(ImageManager imManager){
        imageManager=imManager;
        Image im;
        ImageView imView=new ImageView();
        OnBoardCards obc= ModelManager.getModel().getOnBoardCards();
        for(int i=0; i<obc.getObjectivePublicCardList().size(); i++){
            im=imageManager.getObjectiveCard(obc.getObjectivePublicCardList().get(i).getId());
            imView=(ImageView)cards.getChildren().get(i+obc.getToolCardList().size());
            imView.setImage(im);
        }
        for(int i=0; i<obc.getToolCardList().size(); i++){
            im=imageManager.getToolCard(obc.getToolArrayList().get(i));
            imView=(ImageView)cards.getChildren().get(i);
            imView.setImage(im);
        }
    }

    public void setDisable(){
        for(int i=0; i<cards.getChildren().size(); i++){
            cards.getChildren().get(i).setDisable(true);
        }
    }

    public void setEnable(){
        for(int i=0; i<cards.getChildren().size(); i++){
            cards.getChildren().get(i).setDisable(false);
        }
    }
}
