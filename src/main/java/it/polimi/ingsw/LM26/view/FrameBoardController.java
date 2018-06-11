package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FrameBoardController {

    @FXML
    private TilePane tilepane;
    @FXML
    private VBox background;
    @FXML
    private VBox tokens;
    @FXML
    private Label playername;


    public void setPlayer(PlayerZone pl){
        switch (pl.getIDPlayer()){
            case 0:{background.setStyle("-fx-background-color: #8B0000"); break;}
            case 1:{background.setStyle("-fx-background-color: #035d1a"); break;}
            case 2:{background.setStyle("-fx-background-color: #04075c"); break;}
            case 3:{background.setStyle("-fx-background-color: #5b0350"); break;}
        }
        int n=pl.getWindowPatternCard().getToken();
        n=n-3;
        createTokens(n);
        playername.setText(pl.getName());
    }

    public void useTokens(){

    }

    public void createTokens(int n){
        for(int i=0; i<n; i++){
            Circle token=new Circle();
            token.setRadius(10);
            token.setFill(Color.TRANSPARENT);
            token.setStroke(Color.WHITE);
            tokens.getChildren().add(token);
        }
    }
}
