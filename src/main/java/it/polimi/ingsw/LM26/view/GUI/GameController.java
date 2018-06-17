package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


public class GameController {

    private ImageManager imageManager;

    @FXML
    private ImageView publicCard1;
    @FXML
    private ImageView publicCard2;
    @FXML
    private ImageView publicCard3;
    @FXML
    private ImageView toolCard1;
    @FXML
    private ImageView toolCard2;
    @FXML
    private ImageView toolCard3;
    @FXML
    private ImageView privateCard;
    @FXML
    private TilePane cardsOnBoard;

    public void setupGame(){
        Image im;
        ImageView imView=new ImageView();
        OnBoardCards obc= ModelManager.model.getOnBoardCards();

        imageManager=new ImageManager();
        for(int i=0; i<obc.getObjectivePublicCardList().size(); i++){
            im=imageManager.getObjectiveCard(obc.getObjectivePublicCardList().get(i).getId());
            imView=(ImageView)cardsOnBoard.getChildren().get(i);
            imView.setImage(im);
        }
        for(int i=0; i<obc.getToolCardList().size(); i++){
            im=imageManager.getToolCard(obc.getToolCardList().get(i).getNum());
            imView=(ImageView)cardsOnBoard.getChildren().get(i+obc.getObjectivePublicCardList().size());
            imView.setImage(im);
        }
        privateCard.setImage(imageManager.getObjectiveCard(ModelManager.privateCard.getId()));
    }

}
