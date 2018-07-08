package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ScoreController {

    @FXML
    private VBox scoreTrack;
    @FXML
    private  TilePane row1;
    @FXML
    private  TilePane row2;
    @FXML
    private  TilePane row3;
    @FXML
    private  TilePane row4;
    @FXML
    private Label winlose;
    @FXML
    private Label win;
    @FXML
    private Label scores;



    private ImageManager imageManager;


    public void setUp(String name, int score, String winner, int scoreWinner){

        StackPane cell;

        for(PlayerZone p : ModelManager.getModel().getPlayerList()){
            if(p.getScoreMarker().getPoints()<14){
                if(p.getScoreMarker().getPoints()==0){
                    cell=(StackPane)row1.getChildren().get(0);
                }
                else{
                    cell=(StackPane)row1.getChildren().get(p.getScoreMarker().getPoints()-1);
                }
                showScoreMarker(cell, p);
            }
            else if(p.getScoreMarker().getPoints()>=14 &&p.getScoreMarker().getPoints()<26){
                cell=(StackPane)row2.getChildren().get(p.getScoreMarker().getPoints()-14);
                showScoreMarker(cell, p);
            }
            else if(p.getScoreMarker().getPoints()>=26 &&p.getScoreMarker().getPoints()<39){
                cell=(StackPane)row2.getChildren().get(p.getScoreMarker().getPoints()-26);
                showScoreMarker(cell, p);
            }
            else if(p.getScoreMarker().getPoints()>=39 &&p.getScoreMarker().getPoints()<=50){
                cell=(StackPane)row3.getChildren().get(p.getScoreMarker().getPoints()-39);
                showScoreMarker(cell, p);
            }
            if(name.equals(winner)){
                winlose.setText("Hai vinto!");
                scores.setText(" ");
            }
            else{
                winlose.setText("Hai perso");
                scores.setText(name+": "+score+" punti");
            }
            win.setText("Vince "+name+" con "+scoreWinner+" punti");
        }
    }

    private void showScoreMarker(StackPane stackPane, PlayerZone pl){
        Label l=(Label)stackPane.getChildren().get(0);
        if(pl.getScoreMarker().getRealPoints()>50){
            l.setText("50");
        }
        else l.setText("*");
        if(pl.getIDPlayer()==0){
            stackPane.setStyle("-fx-background-color: #ff3e3e;"+"-fx-border-width: 3;"+"-fx-background-radius: 10;"+"-fx-border-color: #000000;");
        }
        else if(pl.getIDPlayer()==1){
            stackPane.setStyle("-fx-background-color: #29bb16;"+"-fx-border-width: 3;"+"-fx-background-radius: 10;"+"-fx-border-color: #000000;");
        }
        else if(pl.getIDPlayer()==2){
            stackPane.setStyle("-fx-background-color: #4851f4;"+"-fx-border-width: 3;"+"-fx-background-radius: 10;"+"-fx-border-color: #000000;");
        }
        else if(pl.getIDPlayer()==3){
            stackPane.setStyle("-fx-background-color: #d341bd;"+"-fx-border-width: 3;"+"-fx-background-radius: 10;"+"-fx-border-color: #000000;");
        }
    }
}
