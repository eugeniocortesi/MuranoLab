package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.view.GUI.ActionEventGenerator;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private ImageManager imageManager;
    private GameController gController=null;
    private static final int ncol=5;
    private static final int nrow=4;
    private PlayerZone player;
    private ActionEventGenerator aeGenerator;


    public void setUpPlayer(PlayerZone pl, GameController gController, ActionEventGenerator aeGenerator){
        this. gController=gController;
        this.aeGenerator=aeGenerator;
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
        createTokens(n);
        playername.setText(pl.getName());
        if(pl.getIDPlayer()== ModelManager.getId()){
            for(int i=0; i<tilepane.getChildren().size(); i++){
                int d=i;
                tilepane.getChildren().get(i).setOnMouseClicked((MouseEvent event)->handleCellClicked(d+1));
            }
        }
    }

    public void updateFrameBoard(PlayerZone player,ImageManager imageManager){
        this.imageManager=imageManager;
        setGrid(player.getPlayerBoard());
        for(int i=0; i<tokens.getChildren().size()-player.getToken().getTokenNumber(); i++){
            tokens.getChildren().remove(0);
        }
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
          setPatternBox(box);
        }
    }

    private void setPatternBox(Box box){
        ImageView imView=(ImageView) tilepane.getChildren().get(((box.getI()*5)+box.getJ()));
        if(box.getPatternBox().isShade()) imView.setImage(imageManager.getGreyDie(box.getPatternBox().getValue()));
        else{
            if(box.getPatternBox().getColor()== WHITE) {
                imView.setImage(imageManager.getGreyDie(0));
            }
            else{
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

    public void setGrid(WindowFramePlayerBoard frameBoard){
        for(int i=0; i<nrow; i++){
            for(int j=0; j<ncol; j++){
                setBox(frameBoard.getBoardMatrix()[i][j]);
            }
        }
    }

    private void handleCellClicked(int index){
        int r=(Math.floorDiv(index-1, ncol));
        int c=((index-1)%ncol);
        player=ModelManager.getModel().getPlayer(playername.getText());
        Box box=player.getPlayerBoard().getBoardMatrix()[r][c];
        gController.setMoveLabel("Selezionata cella: riga "+(r+1)+", colonna "+(c+1));
        try{
            aeGenerator.frameBoardEvent(box);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void setDisable(boolean disable){
        for(int i=0; i<tilepane.getChildren().size(); i++){
            tilepane.getChildren().get(i).setDisable(disable);
        }
    }

    public void shiftDie(Box from, Box to){
        setPatternBox(from);
        ImageView imTo=(ImageView) tilepane.getChildren().get(((to.getI()*5)+to.getJ()));
        imageManager.setDie(imTo, from.getDie());
    }
}
