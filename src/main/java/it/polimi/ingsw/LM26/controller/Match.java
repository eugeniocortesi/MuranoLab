package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.controller.Testing.CliTest;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.ChangeDieWithTheBag11;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator.DrawOneMoreDie8;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;

public class Match {

    private PlayerZone playing;
    private boolean result = false;
    private Model model;
    private CentralPhase centralPhase;
    private Game game;
    private ControllerInt controller;
    private CliTest cli;


    public Match(Model model, ControllerInt controller) {

        this.controller = controller;
        cli= new CliTest(playing, controller);
        this.game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        this.centralPhase = (CentralPhase) game.getPhase();
        this.model = model;
        this.model.hasChanged();

        play();

    }

    public void play() {

        for (int i = 0; i < 10; i++) {

            playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);

            int k = playing.getIDPlayer();

            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {         //1

                //TODO
                // X4 setPlayerMenu(String name, PlayerZone player)

                //TODO DELETE
                System.out.println(playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                waitCorrectPlayer();

                //controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                if (playing.getActionHistory().isFreezed()) {
                    result = true;
                    System.out.println("this turn you are freezed");
                }

                firstAction();
                //set the correct number of turn 1 0 2

                waitCorrectPlayer();

                if (!playing.getActionHistory().isPlacement() || !playing.getActionHistory().isDieUsed() || !playing.getActionHistory().isCardUsed())
                    result = false;

                if (playing.getActionHistory().isFreezed()) {
                    result = true;
                    System.out.println("this turn you are freezed");
                }

                secondAction();
                //set the correct number of turn 1 0 2

                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), centralPhase.getCurrentRound().getCurrentPlayer());

                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn(), 0);

                result = false;
            }

            //all turn ended
            System.out.println("end turn " + i);

            model.getRoundTrackInt().dump();

            centralPhase.nextRound(centralPhase.getCurrentRound(), game);

            //controller.getView().showTurnEndPhase();
        }

        //controller.getView().showPoints();

    }

    public void firstAction(){

        while (!result) {

            waitCorrectPlayer();

            if (controller.handler(controller.getActionEvent())) {
                System.out.println("done");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();
                // view.showOK()
                model.hasChanged();
                result = true;
            } else System.out.println("match error 1 ");

            controller.setActionEvent(null);

            //view.showNO()

            //view.showReduAction()

        }

    }

    public void secondAction(){

        while (!result) {

            waitCorrectPlayer();

            if (controller.handler(controller.getActionEvent())) {
                System.out.println("done");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                DrawOneMoreDie8 tool8 = (DrawOneMoreDie8) model.getDecks().getToolCardDeck().get(7);

                if (tool8.isNeedPlacement()) {
                    playing.getActionHistory().setPlacement(false);
                    playing.getActionHistory().setDieUsed(false);
                    tool8.setCurrentPlacement(true);
                    playing.getActionHistory().setFreezed(true);
                    System.out.println("choose another die");
                    model.hasChanged();

                } else {
                    model.hasChanged();
                    result = true;
                }
            } else
                System.out.println("match error 2 ");

            controller.setActionEvent(null);

            //view.showNO()

            //view.showReduAction()

        }
    }

    public void waitCorrectPlayer(){

        while (controller.getActionEvent()== null )

            //TODO DELETE
            cli.ask(playing);
            //view.showWrongPlayer();


        while (controller.getActionEvent().getPlayer()!=playing.getIDPlayer()) {
            controller.setActionEvent(null);
            while (controller.getActionEvent() == null)
                //view.showWrongPlayer();
                ;
        }

    }

}
