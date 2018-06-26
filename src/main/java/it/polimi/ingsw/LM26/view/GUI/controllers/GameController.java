package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.*;


public class GameController {

    private ImageManager imageManager;
    private ArrayList<PlayerZone> plListWithoutMe = new ArrayList<PlayerZone>();
    private boolean myTurn;

    @FXML
    private FrameBoardController plZone1Controller;
    @FXML
    private FrameBoardController plZone2Controller;
    @FXML
    private FrameBoardController plZone3Controller;
    @FXML
    private FrameBoardController myFBoardController;
    @FXML
    private OnBoardCardsController onBCardsController;
    @FXML
    private DraftPoolController dPoolController;
    @FXML
    private RoundTrackController rTrackController;
    @FXML
    private TilePane draftAndPlayers;
    @FXML
    private ImageView privateCard;
    @FXML
    private Button endMove;
    @FXML
    private Label instructions;

    @FXML
    public void initialize(){
        imageManager=new ImageManager();
    }

    public void setupGame(boolean myTurn){
        PlayerZone me=null;
        this.myTurn=myTurn;

        dPoolController.setUpDPool(this);
        rTrackController.setUpRTrack(this);

        //imageManager=new ImageManager();
        onBCardsController.setUpCards(imageManager, this);
        privateCard.setImage(imageManager.getObjectiveCard(ModelManager.getPrivateCard().getId()));

        for(PlayerZone i : ModelManager.getModel().getPlayerList()){
            if(i.getIDPlayer()!=ModelManager.getId()) plListWithoutMe.add(i);
            else me=i;
        }
        myFBoardController.setUpPlayer(me, this);
        if(plListWithoutMe.size()>=1){
            plZone1Controller.updateFrameBoard(imageManager);
            plZone1Controller.setUpPlayer(plListWithoutMe.get(0), this);
            if(plListWithoutMe.size()>=2){
                plZone2Controller.updateFrameBoard(imageManager);
                plZone2Controller.setUpPlayer(plListWithoutMe.get(1), this);
                if(plListWithoutMe.size()==3) {
                    plZone3Controller.updateFrameBoard(imageManager);
                    plZone3Controller.setUpPlayer(plListWithoutMe.get(2), this);
                }
            }
        }

    }

    public void setInstructions(String s){
        instructions.setText(s);
    }

    public void updateGame(){
        rTrackController.updateRoundTrack(imageManager);
        dPoolController.updateDPool(imageManager);
        myFBoardController.updateFrameBoard(imageManager);
    }

    public void disableEverything(){
        myFBoardController.setDisable(true);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(true);
        endMove.setDisable(true);
    }

    public void stateFrameBoard(){
        myFBoardController.setDisable(false);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(true);
        endMove.setDisable(false);
    }

    public void stateDraftPool(){
        myFBoardController.setDisable(true);
        dPoolController.setDisable(false);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(true);
        endMove.setDisable(false);
    }

    public void stateRoundTrack(){
        myFBoardController.setDisable(true);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(false);
        endMove.setDisable(false);
    }

    public void stateFBoardCards(){
        myFBoardController.setDisable(false);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(false);
        rTrackController.setDisable(true);
        endMove.setDisable(false);
    }

    public void stateIDNumber(){
        myFBoardController.setDisable(true);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(true);
        endMove.setDisable(false);
    }
}
