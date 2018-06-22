package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.controller.GamePhases.InitialPhase;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.clientConfiguration.DataClientImplementation;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.view.GUI.controllers.ControllerLogin;
import it.polimi.ingsw.LM26.view.GUI.controllers.GameController;
import it.polimi.ingsw.LM26.view.GUI.controllers.WindowPatternController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class View extends ViewInterface{

    private ClientInt clientBase;
    private ClientView clientView;

    DataClientImplementation dataClientImplementation;
    DataClientConfiguration dataClientConfiguration;


    private DisplayableStage displayableStage1 = new DisplayableStage("Login.fxml");
    private DisplayableStage displayableStageNetChioce = new DisplayableStage("NetChioce.fxml");
    private DisplayableStage displayableStageWPattern = new DisplayableStage("WindowPattern.fxml");
    private DisplayableStage displayableStageGame= new DisplayableStage("Game.fxml");
    private Stage stage;
    private ModelManager modelManager;


    public View(Stage stage, ClientInt clientBase) {

        //Initialize client Net
        this.clientBase = clientBase;
        dataClientImplementation = new DataClientImplementation();
        dataClientConfiguration = dataClientImplementation.implementation();
        System.out.println("SocketPort " +dataClientConfiguration.getClientSOCKETPORT()+ " ClientRMI " + dataClientConfiguration.getClientRMIPORT()
                + " ServerRMI "+ dataClientConfiguration.getServerRMIPORT());

        this.stage = stage;

        //TODO remove later
        ModelManager.model=new Model();
        Decks deck=singletonDecks();
        ModelManager.privateCard=deck.getObjectivePrivateCardDeck().get(0);
        ModelManager.setId(1);
        OnBoardCards obc= new OnBoardCards();
        ArrayList<PlayerZone> plList= new ArrayList<PlayerZone>();
        for(int i=0; i<4;i++){
            PlayerZone pl= new PlayerZone("Name", i);
            plList.add(pl);
            pl.setWindowPatternCard(deck.getWindowPatternCardDeck().get(i+3));
        }
       // obc.setPublicCards();
        Game game=new Game(plList, deck, obc);
        game.getPhase().doAction(game, plList);
        /*ArrayList<ObjectivePublicCard> pubc=new ArrayList<ObjectivePublicCard>();
        ArrayList<ToolCard> toolc=new ArrayList<ToolCard>();
        for(int i=0; i<3; i++);{
            pubc.add(deck.getObjectivePublicCardDeck().get(i));
            toolc.add(deck.getToolCardDeck().get(i));
        }*/
        ModelManager.model.setOnBoardCards(obc);
        ModelManager.model.setPlayerList(plList);

        DieInt d;
        Bag bag=new Bag();
        ArrayList<DieInt> dList=new ArrayList<DieInt>();
        for(int j=0; j<5; j++){
            d= bag.draw();
            d.roll();
            dList.add(d);
        }
        DraftPool dPool =new DraftPool();
        dPool.setInDraft(dList);
        ModelManager.model.setDraftPool(dPool);
    }

    @Override
    public void showNetChoise() {
        Platform.runLater(new Runnable() {
            public void run() {
                displayableStageNetChioce.show(stage);
            }
        });

        //TODO add clientVIew initialization
    }

    @Override
    public void showLoginScreen() {
       Platform.runLater(new Runnable() {
           public void run() {
               displayableStage1.show(stage);
           }
       });
    }

    @Override
    public void showLoggedScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.loggedScreen();
    }

    @Override
    public void showAlreadyLoggedScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.alreadyLoggedScreen();
    }

    @Override
    public void showTooManyUsersScreen() {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.tooManyUsersScreen();
    }

    @Override
    public void showAddedPlayer(String s) {
        FXMLLoader fLoader=displayableStage1.getFxmlLoader();
        ControllerLogin cl=fLoader.getController();
        cl.addedPlayer(s);
    }

    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader=displayableStageWPattern.getFxmlLoader();
                WindowPatternController wpController=fLoader.getController();
                wpController.setCardLable(windowDeck);
                displayableStageWPattern.show(stage);
            }
        });
    }

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {

    }

    @Override
    public void showCurrentMenu(String name){
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader=displayableStageGame.getFxmlLoader();
                GameController gController=fLoader.getController();
                gController.setupGame();
                displayableStageGame.show(stage);
            }
        });
    }


    @Override
    public void showAnswerFromController(String name, String answer) {

    }

    @Override
    public void showEndGame(String name, java.lang.Object score) {

    }


    @Override
    public void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model) {

    }

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {

    }

    public void  showCentralPhaseScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                //displayableStage2.show(stage);
            }
        });
    }

    @Override
    public void showDisconnectScreen() {

    }

    @Override
    public void update(Model m) {

    }
}
