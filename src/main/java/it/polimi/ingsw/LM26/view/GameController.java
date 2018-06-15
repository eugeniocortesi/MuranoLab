package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.view.images.ImageManager;
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
        //OnBoardCards obc=View.model.getOnBoardCards();
        //obc.getObjectivePublicCardList().get(0).getId()
        imageManager=new ImageManager();
        //for(ImageView i : cardsOnBoard.)
        Image i=imageManager.getObjectiveCard(29);

        publicCard1.setImage(i);
    }

}
