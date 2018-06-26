package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.xml.ws.Action;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.ANSI_YELLOW;
import static it.polimi.ingsw.LM26.model.PlayArea.Color.WHITE;

public class FrameBoardController {

    @FXML
    private TilePane tilepane;
    @FXML
    private VBox background;
    @FXML
    private VBox tokens;
    @FXML
    private Label playername;

    private ImageManager imageManager=new ImageManager();
    private GameController gController=null;

    public void setMainController(GameController gController){
        this. gController=gController;
    }

    public void setPlayer(PlayerZone pl){
        switch (pl.getScoreMarker().getColor()){
            case ANSI_RED:{background.setStyle("-fx-background-color: #8B0000");
                playername.setStyle("-fx-background-color: #8B0000"); break;}
            case ANSI_GREEN:{background.setStyle("-fx-background-color: #035d1a");
                playername.setStyle("-fx-background-color: #035d1a"); break;}
            case ANSI_BLUE:{background.setStyle("-fx-background-color: #04075c");
                playername.setStyle("-fx-background-color: #04075c"); break;}
            case ANSI_PURPLE:{background.setStyle("-fx-background-color: #5b0350");
                playername.setStyle("-fx-background-color: #5b0350"); break;}
        }
        int n=pl.getWindowPatternCard().getToken();
        n=n-3;
        createTokens(n);
        playername.setText(pl.getName());
        setGrid(pl.getPlayerBoard());
        if(pl.getIDPlayer()== ModelManager.getId()){
            for(int i=0; i<tilepane.getChildren().size(); i++){
                int d=i;
                tilepane.getChildren().get(i).setOnMouseClicked((MouseEvent event)->handleCellClicked(d+1));
            }
        }
    }

    public void updateFrameBoard(){
        PlayerZone me=ModelManager.getModel().getPlayer(ModelManager.getId());
        setGrid(me.getPlayerBoard());
        for(int i=0; i<tokens.getChildren().size()-me.getToken().getTokenNumber(); i++){
            tokens.getChildren().remove(0);
        }
    }

    public void useTokens(){

    }

    private void createTokens(int n){
        for(int i=0; i<n; i++){
            Circle token=new Circle();
            token.setRadius(7);
            token.setFill(Color.TRANSPARENT);
            token.setStroke(Color.WHITE);
            tokens.getChildren().add(token);
        }
    }

    public void setBox(Box box){
        ImageView imView=(ImageView) tilepane.getChildren().get(((box.getI()*5)+box.getJ()));
        if(box.isIsPresent()){
           imageManager.setDie(imView, box.getDie());
        }
        else{
            if(box.getPatternBox().isShade()) imView.setImage(imageManager.getGreyDie(box.getPatternBox().getValue()));
            else{
                if(box.getPatternBox().getColor()!= WHITE){
                    switch (box.getPatternBox().getColor()){
                        case ANSI_YELLOW:{imView.setImage(imageManager.getYellowDie(0)); break;}
                        case ANSI_BLUE:{imView.setImage(imageManager.getBlueDie(0)); break;}
                        case ANSI_RED:{imView.setImage(imageManager.getRedDie(0)); break;}
                        case ANSI_PURPLE:{imView.setImage(imageManager.getPurpleDie(0)); break;}
                        case ANSI_GREEN:{imView.setImage(imageManager.getGreenDie(0)); break;}
                    }
                }
            }
        }
    }

    public void setGrid(WindowFramePlayerBoard frameBoard){
        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                setBox(frameBoard.getBoardMatrix()[i][j]);
            }
        }
    }

    public void handleCellClicked(int index){
        System.out.println(index);
        gController.setInstructions(Integer.toString(index));
    }

    public void setDisable(boolean disable){
        for(int i=0; i<tilepane.getChildren().size(); i++){
            tilepane.getChildren().get(i).setDisable(disable);
        }
    }
}
