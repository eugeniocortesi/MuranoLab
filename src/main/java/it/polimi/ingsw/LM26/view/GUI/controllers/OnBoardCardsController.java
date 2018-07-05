package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.view.GUI.ActionEventGenerator;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import javax.jws.WebParam;


public class OnBoardCardsController {

    private ImageManager imageManager;
    private GameController gController;
    private ActionEventGenerator aeGenerator;
    private OnBoardCards obc;

    @FXML
    private TilePane cards;
    @FXML
    private TilePane tokens;

    public void setUpCards(ImageManager imManager, GameController gController, ActionEventGenerator aeGenerator){
        imageManager=imManager;
        this.aeGenerator=aeGenerator;
        Image im;
        ImageView imView;
        this. gController=gController;
        obc= ModelManager.getModel().getOnBoardCards();
        for(int i=0; i<obc.getToolArrayList().size(); i++){
            im=imageManager.getToolCard(obc.getToolArrayList().get(i));
            imView=(ImageView)cards.getChildren().get(i);
            imView.setImage(im);
            int n=i;
            imView.setOnMouseClicked((MouseEvent event)->handleCardClicked(n));
        }
        for(int i=0; i<obc.getObjectivePublicCardList().size(); i++){
            im=imageManager.getObjectiveCard(obc.getObjectivePublicCardList().get(i).getId());
            imView=(ImageView)cards.getChildren().get(i+obc.getToolArrayList().size());
            imView.setImage(im);
        }
    }

    public void updateCardsToken(){
        for(int i=0; i<tokens.getChildren().size(); i++){
            Label numToken=(Label) tokens.getChildren().get(i);
            if(ModelManager.getModel().getOnBoardCards().getCardsToken()[i]==1){
                numToken.setText("Costo: 1 Segnalino Favore");
            }
            else numToken.setText("Costo: 2 Segnalini Favore");

        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<cards.getChildren().size(); i++){
            cards.getChildren().get(i).setDisable(disable);
        }
    }

    public void handleCardClicked(int idx){
        gController.setMoveLabel("Selezionata carta "+(idx+1));
        try{
            aeGenerator.cardEvent(idx);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
