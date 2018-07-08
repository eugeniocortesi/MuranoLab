package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.view.GUI.ActionEventGenerator;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RoundTrackController {

    private ImageManager imManager;
    private GameController gController;
    private ArrayList<RoundTrackTurn> roundTrackList;
    private ActionEventGenerator aeGenerator;

    @FXML
    private TilePane roundTrack;
    @FXML
    private TilePane diceLists;

    public void setUpRTrack(GameController gController, ActionEventGenerator aeGenerator){
        this.gController=gController;
        this.aeGenerator=aeGenerator;
    }

    public void updateRoundTrack(ImageManager imageManager){
        this.imManager=imageManager;
        roundTrackList = ModelManager.getModel().getRoundTrackInt().getRoundTrackTurnList();
        for(int i=0; i<roundTrackList.size(); i++){
            if(roundTrackList.get(i).getDiceList().get(0)!=null){
                ImageView num=keepFirstImage(i);
                int r=i;
                num.setOnMouseClicked((MouseEvent event) ->handleDieClicked(r));
                imageManager.setDie(num, roundTrackList.get(i).getDiceList().get(0));
            }
            else {
                ImageView num=keepFirstImage(i);
                try{
                    num.setImage(null);
                }catch(RuntimeException e){}
            }
            if(roundTrackList.get(i).getDiceList().size()>1){
                VBox diceList=(VBox) diceLists.getChildren().get(i);
                for(int j=1; j<roundTrackList.get(i).getDiceList().size(); j++){
                    ImageView newDie= new ImageView();
                    newDie.setFitHeight(40);
                    newDie.setFitWidth(40);
                    imageManager.setDie(newDie, roundTrackList.get(i).getDiceList().get(j));
                    int n=j, m=i;
                    newDie.setOnMouseClicked((MouseEvent event)->handleDieClicked(9+n+(m*8)));
                    diceList.getChildren().add(newDie);
                }
                int n=diceList.getChildren().size()-(roundTrackList.get(i).getDiceList().size()-1);
                for(int k=0; k<n; k++){
                    diceList.getChildren().remove(diceList.getChildren().size()-1);
                }
            }
        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<roundTrack.getChildren().size(); i++){
            ImageView num=keepFirstImage(i);
            num.setDisable(disable);
        }
        for(int i=0; i<diceLists.getChildren().size(); i++){
            VBox diceList=(VBox) diceLists.getChildren().get(i);
            for(int j=0; j<diceList.getChildren().size(); j++){
                diceList.getChildren().get(j).setDisable(disable);
            }
        }
    }

    private ImageView keepFirstImage(int idx){
        VBox cell=(VBox) roundTrack.getChildren().get(idx);
        StackPane sp=(StackPane) cell.getChildren().get(1);
        return (ImageView) sp.getChildren().get(0);
    }

    private void handleDieClicked(int idx){
        DieInt die;
        int turn, dieidx;
        if(idx<=9){
            die=roundTrackList.get(idx).getDiceList().get(0);
            turn= idx;
            dieidx=0;
            gController.setMoveLabel("rt: prima riga, posto "+(idx+1));
        }
        else{
            die=roundTrackList.get(Math.floorDiv((idx-9),8)).getDiceList().get((idx-9)%8);
            turn= Math.floorDiv((idx-9),8);
            dieidx=(idx-9)%8;
            gController.setMoveLabel("rt: riga"+Math.floorDiv((idx-9),8)+", posto "+((idx-9)%8)+"indice "+idx);
        }
        try{
            aeGenerator.roundTrackEvent(die, turn, dieidx);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
