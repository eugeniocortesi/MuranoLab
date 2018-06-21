package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PlayArea.Color.*;


public class GameController {

    private ImageManager imageManager;
    private ArrayList<PlayerZone> plListWithoutMe = new ArrayList<PlayerZone>();

    @FXML
    private ImageView privateCard;
    @FXML
    private TilePane cardsOnBoard;
    @FXML
    private FrameBoardController plZone1Controller;
    @FXML
    private FrameBoardController plZone2Controller;
    @FXML
    private FrameBoardController plZone3Controller;
    @FXML
    private TilePane draftAndPlayers;
    @FXML
    private TilePane draftPool;
    @FXML
    private TilePane roundTrack;

    public void setupGame(){
        Image im;
        ImageView imView=new ImageView();
        OnBoardCards obc= ModelManager.getModel().getOnBoardCards();

        imageManager=new ImageManager();
        for(int i=0; i<obc.getObjectivePublicCardList().size(); i++){
            im=imageManager.getObjectiveCard(obc.getObjectivePublicCardList().get(i).getId());
            imView=(ImageView)cardsOnBoard.getChildren().get(i);
            imView.setImage(im);
        }
        for(int i=0; i<obc.getToolCardList().size(); i++){
            im=imageManager.getToolCard(obc.getToolArrayList().get(i));
            imView=(ImageView)cardsOnBoard.getChildren().get(i+obc.getObjectivePublicCardList().size());
            imView.setImage(im);
        }
        privateCard.setImage(imageManager.getObjectiveCard(ModelManager.getPrivateCard().getId()));

        for(PlayerZone i : ModelManager.getModel().getPlayerList()){
            if(i.getIDPlayer()!=ModelManager.getId()) plListWithoutMe.add(i);
        }
        if(plListWithoutMe.size()>=1){
            plZone1Controller.setPlayer(plListWithoutMe.get(0));
            if(plListWithoutMe.size()>=2){
                plZone2Controller.setPlayer(plListWithoutMe.get(1));
                if(plListWithoutMe.size()==3) {
                    plZone3Controller.setPlayer(plListWithoutMe.get(2));
                }
            }
        }
        for(int i=0; i< ModelManager.getModel().getDraftPool().getInDraft().size(); i++){
            ImageView dieImage=(ImageView) draftPool.getChildren().get(i);
            imageManager.setDie(dieImage, ModelManager.getModel().getDraftPool().getInDraft().get(i));
        }
    }

    public void updateRoundTrack(){

    }

}
