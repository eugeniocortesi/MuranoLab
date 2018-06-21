package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;

public class Match extends Thread {

    private PlayerZone playing;
    private boolean result = false;
    private Model model;
    private PhaseInt centralPhase;
    private Game game;
    private ControllerInt controller;
    private CliTest cli;
    private static final Logger LOGGER = Logger.getLogger(Match.class.getName());
    private ActionEvent event;

    public Match(Model model, ControllerInt controller) {

        this.controller = controller;
        cli= new CliTest(playing, controller);
        this.game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        this.centralPhase = game.getPhase();
        this.model = model;
        this.model.hasChanged();

        //play();

    }

    public void run(){
        play();
    }

    public void play() {

        for (int i = 0; i < 10; i++) {

            playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn());

            int k = playing.getIDPlayer();

            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {         //1

                //TODO
                // X4 setPlayerMenu(String name, PlayerZone player)

                //TODO DELETE
                LOGGER.log(Level.SEVERE, playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                waitCorrectPlayer();
                LOGGER.log(Level.WARNING, "Action event and player ok");

                if(playing.getPlayerState()!=STANDBY) {

                    if (playing.getActionHistory().isFreezed()) {
                        result = true;
                        System.out.println("this turn you are freezed");
                    } else {
                        firstAction();
                        //set the correct number of turn 1 0 2

                        waitCorrectPlayer();
                        if (playing.getPlayerState() != STANDBY) {

                            if (!playing.getActionHistory().isPlacement() || !playing.getActionHistory().isDieUsed() || !playing.getActionHistory().isCardUsed())
                                result = false;

                            if (playing.getActionHistory().isFreezed()) {
                                result = true;
                                System.out.println("this turn you are freezed");
                            } else

                                secondAction();
                            //set the correct number of turn 1 0 2
                        }
                    }
                }
                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), centralPhase.getCurrentRound().getCurrentPlayer());

                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn());

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


            if (playing.getPlayerState() != STANDBY) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();
                    // view.showOK()
                    model.hasChanged();
                    result = true;
                } else {
                    System.out.println("match error 1 ");
                    waitCorrectPlayer();
                }

                //view.showNO()

                //view.showReduAction()

            }else result = true;
        }

    }

    public void secondAction(){

        while (!result) {

            if (playing.getPlayerState() != STANDBY) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();

                    if (model.getRestrictions().isTool8needPlacement()) {
                        playing.getActionHistory().setPlacement(false);
                        playing.getActionHistory().setDieUsed(false);
                        model.getRestrictions().setCurrentPlacement(true);
                        playing.getActionHistory().setFreezed(true);
                        System.out.println("choose another die");
                        model.hasChanged();
                        waitCorrectPlayer();

                    } else {
                        model.hasChanged();
                        result = true;
                    }
                } else {
                    System.out.println("match error 2 ");
                    waitCorrectPlayer();

                }

                model.getRestrictions().resetRestrictions();

                //view.showNO()

                //view.showReduAction()

            }else result=true;
        }
    }

    public void waitCorrectPlayer(){

        event = controller.getActionEvent();
        LOGGER.log(Level.WARNING, "Event match: " + event);
        if(playing.getPlayerState()!= STANDBY)
        LOGGER.log(Level.WARNING, "playing is ok");
        while (event == null && playing.getPlayerState()!=STANDBY){
            event = controller.getActionEvent();
        }
            LOGGER.log(Level.WARNING,"exit while ");

            //TODO DELETE
            //cli.ask(playing);
            //view.showWrongPlayer();

        if (playing.getPlayerState() != STANDBY)
            while (event.getPlayer()!=playing.getIDPlayer() && playing.getPlayerState()!=STANDBY) {
                event = controller.getActionEvent();
                while (event == null && playing.getPlayerState()!=STANDBY)
                    System.out.println("entering here");
                    //view.showWrongPlayer();
                    ;
            }

    }

}
