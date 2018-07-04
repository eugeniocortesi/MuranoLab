package it.polimi.ingsw.LM26.view.GUI;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.modelView.ObservableSimple;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientInt;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ClientView;
import it.polimi.ingsw.LM26.systemNetwork.clientNet.ViewInterface;
import it.polimi.ingsw.LM26.view.GUI.controllers.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;


public class View extends ViewInterface {

    private static ClientInt clientBase;
    private static ClientView clientView;
    private boolean myTurn;
    private boolean begin=true;


    private DisplayableStage displayableStageLogin = new DisplayableStage("Login.fxml");
    private DisplayableStage displayableStageNetChioce = new DisplayableStage("NetChioce.fxml");
    private DisplayableStage displayableStageWPattern = new DisplayableStage("WindowPattern.fxml");
    private DisplayableStage displayableStageGame = new DisplayableStage("GamePhase.fxml");
    private DisplayableStage displayableStageScores = new DisplayableStage("Scores.fxml");
    private DisplayableStage displayableStageDisconnection = new DisplayableStage("Disconnection.fxml");
    private Stage primaryStage;


    public View(Stage primaryStage, ClientInt clientBase) {
        this.primaryStage=primaryStage;
        //Initialize client Net
        View.clientBase = clientBase;

        //TODO remove later
       /* ModelManager.model = new Model();
        Decks deck =loadDecks();
        ModelManager.privateCard = deck.getObjectivePrivateCardDeck().get(0);
        ModelManager.setId(0);
        OnBoardCards obc = new OnBoardCards();
        ArrayList<PlayerZone> plList = new ArrayList<PlayerZone>();
        for (int i = 0; i < 4; i++) {
            PlayerZone pl = new PlayerZone("Name", i);
            plList.add(pl);
            pl.setWindowPatternCard(deck.getWindowPatternCardDeck().get(i + 3));
        }
        plList.get(0).setPlayerState(PlayerState.BEGINNING);
        Game game = new Game(plList, deck, obc);
        game.getPhase().doAction(game, plList);


        ModelManager.model.setOnBoardCards(obc);
        ModelManager.model.setPlayerList(plList);

        DieInt d;
        Bag bag = new Bag();
        ArrayList<DieInt> dList = new ArrayList<DieInt>();
        for (int j = 0; j < 5; j++) {
            d = bag.draw();
            d.roll();
            dList.add(d);
        }
        DraftPool dPool = new DraftPool();
        dPool.setInDraft(dList);
        ModelManager.model.setDraftPool(dPool);
        RoundTrackInt rTrack = new RoundTrack();
        for (int f = 0; f < 5; f++) {
            rTrack.addDice(dList);
        }
        ModelManager.model.setRoundTrackInt(rTrack);
        this.update(ModelManager.model);*/
    }

    public static ClientInt getClientBase() {
        return clientBase;
    }

    public static void setClientBase(ClientInt clientBase) {
        View.clientBase = clientBase;
    }

    public static ClientView getClientView() {
        return clientView;
    }

    public static void setClientView(ClientView clientView) {
        View.clientView = clientView;
    }

    @Override
    public void showNetChoise() {
        FXMLLoader fxmlLoader = displayableStageNetChioce.getFxmlLoader();
        ControllerNetChoice cNetChoice = fxmlLoader.getController();
        cNetChoice.setClientViewBase(this);
        Platform.runLater(new Runnable() {
            public void run() {
                displayableStageNetChioce.show(primaryStage);
            }
        });

        //TODO add clientVIew initialization
    }

    @Override
    public void showLoginScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fxmlLoader = displayableStageLogin.getFxmlLoader();
                ControllerLogin cLogin = fxmlLoader.getController();
                displayableStageLogin.show(primaryStage);
            }
        });
    }

    @Override
    public void showLoggedScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageLogin.getFxmlLoader();
                ControllerLogin cl = fLoader.getController();
                cl.loggedScreen();
            }
        });
    }

    @Override
    public void showAlreadyLoggedScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageLogin.getFxmlLoader();
                ControllerLogin cl = fLoader.getController();
                cl.alreadyLoggedScreen();
            }
        });
    }

    @Override
    public void showTooManyUsersScreen() {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageLogin.getFxmlLoader();
                ControllerLogin cl = fLoader.getController();
                cl.tooManyUsersScreen();
            }
        });
    }

    @Override
    public void showAddedPlayer(String s) {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageLogin.getFxmlLoader();
                ControllerLogin cl = fLoader.getController();
                cl.addedPlayer(s);
            }
        });
    }

    @Override
    public void showWindowPattern(String user, int id, ArrayList<WindowPatternCard> windowDeck) {
        ModelManager.id = id;
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageWPattern.getFxmlLoader();
                WindowPatternController wpController = fLoader.getController();
                wpController.setCardLable(windowDeck, user);
                displayableStageWPattern.show(primaryStage);
            }
        });
    }

    @Override
    public void showSetPlayerMenu(String name, PlayerZone player) {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader = displayableStageGame.getFxmlLoader();
                GameController gController = fLoader.getController();
                if (player.getPlayerState().equals(PlayerState.BEGINNING)) {
                    myTurn = true;
                } else myTurn = false;
                gController.isMyTurn(myTurn);
                showCurrentMenu(null);
            }
        });
    }


    @Override
    public void showCurrentMenu(String name){
        if(begin){
            FXMLLoader fLoader=displayableStageGame.getFxmlLoader();
            GameController gController=fLoader.getController();
            gController.setupGame(myTurn, this);
            Platform.runLater(new Runnable() {
                public void run() {
                    displayableStageGame.show(primaryStage);
                }
            });
        }
        begin=false;
    }


    @Override
    public void showAnswerFromController(String name, String answer) {
        FXMLLoader fLoader=displayableStageGame.getFxmlLoader();
        GameController gc=fLoader.getController();
        gc.setInstructions(answer);
    }

    @Override
    public void showEndGame(String name, java.lang.Object score) {
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader=displayableStageScores.getFxmlLoader();
                ScoreController sc=fLoader.getController();
                sc.setUp();
                displayableStageScores.show(primaryStage);
            }
        });
    }


    @Override
    public void startAcceptor(it.polimi.ingsw.LM26.observers.serverController.Observer observer, ObservableSimple model) {

    }

    @Override
    public void showPrivateCard(String name, ObjectivePrivateCard privateCard) {
        ModelManager.privateCard=privateCard;
    }

    @Override
    public void showDisconnectScreen() {
        Platform.runLater(() ->
        {
            Stage discStage=new Stage();
            discStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fLoader=displayableStageDisconnection.getFxmlLoader();
            DisconnectionController dc=fLoader.getController();
            dc.disconnectionSetUp(discStage);
            displayableStageDisconnection.showAndWait(primaryStage);
        });
    }


    @Override
    public void update(Model m) {
        ModelManager.model=m;
        Platform.runLater(new Runnable() {
            public void run() {
                FXMLLoader fLoader=displayableStageGame.getFxmlLoader();
                GameController gController=fLoader.getController();
                gController.updateGame();
            }
        });
    }

    public void notifyActionEvent(ActionEvent ae){
        notify(ae);
    }
}
