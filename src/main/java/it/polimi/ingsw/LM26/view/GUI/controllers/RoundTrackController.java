package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RoundTrackController {

    private ImageManager imManager;
    private GameController gController;

    @FXML
    private TilePane roundTrack;
    @FXML
    private  TilePane diceLists;

    public void setMainController(GameController gController){
        this. gController=gController;
    }

    public void updateRoundTrack(ImageManager imageManager){
        this.imManager=imageManager;
        ArrayList<RoundTrackTurn> roundTrackList = ModelManager.getModel().getRoundTrackInt().getRoundTrackTurnList();
        for(int i=0; i<roundTrackList.size(); i++){
            VBox cell=(VBox) roundTrack.getChildren().get(i);
            StackPane sp=(StackPane) cell.getChildren().get(1);
            ImageView num=(ImageView) sp.getChildren().get(0);
            imageManager.setDie(num, roundTrackList.get(i).getDiceList().get(0));
            if(roundTrackList.get(i).getDiceList().size()>1){
                VBox diceList=(VBox) diceLists.getChildren().get(i);
                for(int j=1; j<roundTrackList.get(i).getDiceList().size(); j++){
                    ImageView newDie= new ImageView();
                    newDie.setFitHeight(40);
                    newDie.setFitHeight(40);
                    imageManager.setDie(newDie, roundTrackList.get(i).getDiceList().get(j));
                    diceList.getChildren().add(newDie);
                }
            }
        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<roundTrack.getChildren().size(); i++){
            VBox cell=(VBox) roundTrack.getChildren().get(i);
            StackPane sp=(StackPane) cell.getChildren().get(1);
            ImageView num=(ImageView) sp.getChildren().get(0);
            num.setDisable(disable);
        }
        for(int i=0; i<diceLists.getChildren().size(); i++){
            VBox diceList=(VBox) diceLists.getChildren().get(i);
            for(int j=1; j<diceList.getChildren().size(); j++){
                diceList.getChildren().get(j).setDisable(disable);
            }
        }
    }

}
