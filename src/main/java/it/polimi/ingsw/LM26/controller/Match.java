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
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerActionPlayer;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerImplementation;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionEvent;

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
    private TimerActionPlayer timerActionPlayer;
    private static final Logger LOGGER = Logger.getLogger(Match.class.getName());
    private ActionEvent event;
    private int i=0;

    public Match(Model model, ControllerInt controller) {

        this.controller = controller;
        this.game = new Game(model.getPlayerList(), model.getDecks(), model.getOnBoardCards());  //initialPhase
        game.getPhase().doAction(game, model.getPlayerList());    //centralPhase
        this.centralPhase = game.getPhase();
        this.model = model;
        this.model.hasChanged();

        //Taking end timer in milliseconds from file
        TimerImplementation timerImplementation = new TimerImplementation();
        TimerConfiguration timerConfiguration = timerImplementation.implentation();
        timerActionPlayer = new TimerActionPlayer(timerConfiguration.getTimerEnd());
    }

    public void run() {

        play();
    }

    public void play() {

        //for (int i = 0; i < model.getPlayerList().size(); i++)

            //controller.getViewGameInterface().showSetPlayerMenu(model.getPlayerList().get(i).getName(), model.getPlayerList().get(i));

        while(i<game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn());

            while (centralPhase.getCurrentRound().getRoundState() != FINISHED) {

                //TODO DELETE
                System.out.println("              CHANGE TURN: " + playing.getName());
                LOGGER.log(Level.SEVERE, playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                waitEvent();

                if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && !playing.getActionHistory().isFreezed()) {

                    firstAction();

                    if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                        result = false;

                        controller.getViewGameInterface().showCurrentMenu(playing.getName());

                        waitEvent();

                        if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump())

                            secondAction();
                    }

                } else if (playing.getActionHistory().isFreezed()) {

                    System.out.println("this turn you are freezed");

                    //controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Salti il turno");
                }

                //PlayerZone playerEnding = playing;

                centralPhase.getCurrentRound().endAction(centralPhase.getTurn(), model.getRoundTrackInt(), model.getDraftPool(), playing);

                playing = centralPhase.getCurrentRound().nextPlayer(model.getPlayerList(), centralPhase.getTurn());

                //controller.getViewGameInterface().showSetPlayerMenu(playerEnding.getName(), playerEnding);

                result = false;
            }

            //all turn ended
            System.out.println("end turn " + i);

            model.getRoundTrackInt().dump();

            centralPhase.nextRound(centralPhase.getCurrentRound(), game);

            //controller.getView().showTurnEndPhase();
        }

        game.getPhase().doAction(game, model.getPlayerList());

        System.out.println("Il vincitore è " + game.getPhase().getWinner().getName());  // final phase

        controller.declareWinner(game.getPhase().getWinner());
    }

    public void firstAction() {

        System.out.println("        FIRST ACTION ");

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    //if(event.getId()==11)controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Passi il turno ");
                    //else controller.getViewGameInterface().showAnswerFromController (playing.getName(),"OK");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();
                    // view.showOK()
                    model.hasChanged();
                    result = true;
                    //from card 11
                    //if(model.getRestrictions().getColor()!=null)
                    //controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Hai estratto un dado "+model.getRestrictions().getColor());
                } else {
                    System.out.println("match error 1 ");
                    //controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Errore: vuoi riprovare?");
                    controller.getViewGameInterface().showCurrentMenu(playing.getName());
                    waitEvent();
                }

                //view.showNO()

                //view.showReduAction()

            } else result = true;
        }

    }

    public void secondAction() {

        System.out.println("        SECOND ACTION ");

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {
                if (controller.handler(event)) {
                    System.out.println("done");
                    //if(event.getId()==11)controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Passi ");
                    //else controller.getViewGameInterface().showAnswerFromController (playing.getName(),"OK");
                    playing.getPlayerBoard().printCard();
                    System.out.println("DraftPool");
                    model.getDraftPool().printDraftPool();

                    if (model.getRestrictions().isTool8needPlacement()) {
                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setJump(false);
                        playing.getActionHistory().setPlacement(false);
                        playing.getActionHistory().setDieUsed(false);
                        model.getRestrictions().setCurrentPlacement(true);
                        playing.getActionHistory().setFreezed(true);
                        System.out.println("choose another die");
                        //controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Ok, scegli un altro dado");
                        model.hasChanged();
                        controller.getViewGameInterface().showCurrentMenu(playing.getName());
                        waitEvent();
                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);

                    } else {
                        model.hasChanged();
                        result = true;
                    }
                } else {
                    System.out.println("match error 2 ");
                    //controller.getViewGameInterface().showAnswerFromController (playing.getName(),"Errore: vuoi riprovare?");
                    controller.getViewGameInterface().showCurrentMenu(playing.getName());
                    waitEvent();
                    if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);

                }

                //view.showNO()

                //view.showReduAction()

            } else result = true;
        }
    }

    public void waitEvent() {

        waitCorrectPlayer();

        while (event!=null && event.getId() == 12) {

            System.out.println("        SHOWING MENU to player " + event.getPlayer());

            controller.handler(event);

            waitCorrectPlayer();
        }
    }

    public void waitCorrectPlayer() {

        if (playing.getActionHistory().isJump()) System.out.println("ERROR: you don't need to wait an event ");

        event = controller.getActionEvent();

        LOGGER.log(Level.WARNING, "Event match: " + event);

        TimerTaskActionEvent ttask1= timerActionPlayer.scheduleTimerActionPlayer(controller.getSetupHandler(), playing.getName());

        while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

            event = controller.getActionEvent();
        }

        if(!playing.getActionHistory().isJump() && ttask1!=null){

            ttask1.setArrived(true);
        }

        while (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && event.getPlayer() != playing.getIDPlayer() && event.getId() != 12) {

            TimerTaskActionEvent ttask2= timerActionPlayer.scheduleTimerActionPlayer(controller.getSetupHandler(), playing.getName());

            System.out.println("THIS IS NOT THE RIGHT PLAYER: EVENT REFUSED ");

            //view.showWrongPlayer()

            event = controller.getActionEvent();

            while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                event = controller.getActionEvent();
            }

            if(!playing.getActionHistory().isJump() && ttask2!=null){

                ttask2.setArrived(true);
            }
        }

        if (playing.getActionHistory().isJump()) System.out.println("STOP WAITING because the player end his time");

        if (playing.getPlayerState() == STANDBY) System.out.println("STOP WAITING because the player went in STANDBY");
    }

    public PhaseInt getGame() {

        return game.getPhase();
    }
}