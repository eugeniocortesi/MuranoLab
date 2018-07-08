package it.polimi.ingsw.LM26.view.GUI.controllers;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackTurn;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObserverSimple;
import it.polimi.ingsw.LM26.view.GUI.ActionEventGenerator;
import it.polimi.ingsw.LM26.view.GUI.GameState;
import it.polimi.ingsw.LM26.view.GUI.ModelManager;
import it.polimi.ingsw.LM26.view.GUI.View;
import it.polimi.ingsw.LM26.view.GUI.images.ImageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private PlayerZone me=null;
    private ActionEventGenerator aeGenerator;
    private GameState gameState;
    private View view;

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
    private Button endToolCard;
    @FXML
    private Label instructions;
    @FXML
    private Label instructionsController;
    @FXML
    private Label move;
    @FXML
    private Menu inDecrement;
    @FXML
    private Menu dieValue;


    public void setupGame(boolean myTurn, View view){
        imageManager=new ImageManager();
        aeGenerator=new ActionEventGenerator(this, view);
        this.myTurn=myTurn;
        this.view=view;

        dPoolController.setUpDPool(this);
        rTrackController.setUpRTrack(this, aeGenerator);

        onBCardsController.setUpCards(imageManager, this, aeGenerator);
        privateCard.setImage(imageManager.getObjectiveCard(ModelManager.getPrivateCard().getId()));

        for(PlayerZone i : ModelManager.getModel().getPlayerList()){
            if(i.getIDPlayer()!=ModelManager.getId()) plListWithoutMe.add(i);
            else me=i;
        }
        myFBoardController.setUpPlayer(me, this, aeGenerator);
        if(plListWithoutMe.size()>=1){
            plZone1Controller.setUpPlayer(plListWithoutMe.get(0), this, aeGenerator);
            if(plListWithoutMe.size()>=2){
                plZone2Controller.setUpPlayer(plListWithoutMe.get(1), this, aeGenerator);
                if(plListWithoutMe.size()==3) {
                    plZone3Controller.setUpPlayer(plListWithoutMe.get(2), this, aeGenerator);
                }
            }
        }
        for(int i=0; i<inDecrement.getItems().size(); i++){
            int n=i;
            inDecrement.getItems().get(i).setOnAction((ActionEvent event)->handleInDecrement(n));
        }
        for(int i=0; i<dieValue.getItems().size(); i++){
            int n=i;
            dieValue.getItems().get(i).setOnAction((ActionEvent event)->handleDieValue(n+1));
        }
        this.isMyTurn(myTurn);
        this.updateGame();
    }

    public void isMyTurn(boolean myTurn){
        if(me!=null){
            if(!myTurn) {
                setInstructions("Turno di un altro giocatore");
                gameState=GameState.DONOTHING;
            }
            else setUpState(GameState.BEGINMOVE);
        }
    }

    public void setInstructions(String s){
        instructions.setText(s);
    }

    public void setInstructionsController(String s) {instructionsController.setText(s);}

    public void setMoveLabel(String s) {move.setText(s);}

    public void updateGame(){
        if(me!=null){
            rTrackController.updateRoundTrack(imageManager);
            dPoolController.updateDPool(imageManager);
            onBCardsController.updateCardsToken();
            myFBoardController.updateFrameBoard(ModelManager.getModel().getPlayer(me.getIDPlayer()), imageManager);
            plZone1Controller.updateFrameBoard(ModelManager.getModel().getPlayer(plListWithoutMe.get(0).getIDPlayer()), imageManager);
            if(plListWithoutMe.size()>1){
                plZone2Controller.updateFrameBoard(ModelManager.getModel().getPlayer(plListWithoutMe.get(1).getIDPlayer()), imageManager);
                if(plListWithoutMe.size()>2){
                    plZone3Controller.updateFrameBoard(ModelManager.getModel().getPlayer(plListWithoutMe.get(2).getIDPlayer()), imageManager);
                }
            }
            setUpState(gameState);
        }
    }

    private void disableEverything(){
        myFBoardController.setDisable(true);
        dPoolController.setDisable(true);
        onBCardsController.setDisable(true);
        rTrackController.setDisable(true);
        inDecrement.setDisable(true);
        dieValue.setDisable(true);
        endMove.setDisable(true);
        endToolCard.setDisable(true);
    }

    public void setUpState(GameState gState){
        gameState=gState;
        disableEverything();
        switch(gState){
            case BEGINMOVE:
                dPoolController.setDisable(false);
                onBCardsController.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Puoi posizionare un dado o usare una carta strumento");
                break;
            case CONTINUECHOICE:
                endToolCard.setDisable(false);
            case FRAMEBOARD:
                myFBoardController.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Scegli sulla Plancia Vetrata");
                break;
            case DRAFTPOOL:
                dPoolController.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Scegli un dado dalla Riserva");
                break;
            case ROUNDTRACK:
                rTrackController.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Scegli un dado dal tracciato dei round");
                break;
            case INDECREMENT:
                inDecrement.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Scegli se incrementare o decrementare il valore del dado");
                break;
            case DIEVALUE:
                dieValue.setDisable(false);
                endMove.setDisable(false);
                setInstructions("Scegli il valore del dado");
                break;
            case DONOTHING:
                disableEverything();
                break;
            case ACTIONEVENT: break;
        }
    }

    public void handleEndMove(ActionEvent event){
        aeGenerator.endTurn();
    }

    public void handleEndToolCard(ActionEvent event){
        aeGenerator.endTCardMove();
    }

    private void handleInDecrement(int idx){
        String s;
        if(idx==0) s="increment";
        else s="decrement";
        try{
            aeGenerator.indecrementEvent(s);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    private void handleDieValue(int idx){
        try {
            aeGenerator.dieValueEvent(idx);
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void sendDPoolEvent(DieInt die){
        try{
            aeGenerator.draftPoolEvent(die, gameState);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void moveDieTemporarilyFrameB(Box f, Box t){
        myFBoardController.shiftDie(f,t);
    }
}
