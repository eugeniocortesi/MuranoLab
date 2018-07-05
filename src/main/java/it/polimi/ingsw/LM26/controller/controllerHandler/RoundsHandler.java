package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerActionPlayer;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerConfiguration;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerImplementation;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerTaskActionEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.controller.GamePhases.RoundState.FINISHED;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;

public class RoundsHandler extends Thread {

    private PlayerZone playing;

    private boolean result = false;

    private Model model;

    private Game game;

    private ControllerInt controller;

    private TimerActionPlayer timerActionPlayer;

    private TimerTaskActionEvent ttask1;

    private static final Logger LOGGER = Logger.getLogger(RoundsHandler.class.getName());

    private ActionEvent event;

    private int i = 0;

    private PlayerZone playerEnding;


    public RoundsHandler(Model model, ControllerInt controller) {

        this.controller = controller;

        this.game = new Game();  //initialPhase

        game.getPhase().doAction(game);    //centralPhase

        this.model = model;

        this.model.hasChanged();

        //Taking end timer in milliseconds from file
        TimerImplementation timerImplementation = new TimerImplementation();

        TimerConfiguration timerConfiguration = timerImplementation.implentation();

        timerActionPlayer = new TimerActionPlayer(timerConfiguration.getTimerEnd());
    }

    @Override
    public void run() {

        play();
    }

    public void play() {

        for (int j = 0; j < model.getPlayerList().size(); j++)

            controller.getViewGameInterface().showSetPlayerMenu(model.getPlayerList().get(j).getName(), model.getPlayerList().get(j));

        while (i < game.getPhase().getNrounds() && !game.getPhase().getOnePlayer()) {

            playing = game.getPhase().getCurrentRound().nextPlayer();

            model.hasChanged();

            for (int j = 0; j < model.getPlayerList().size(); j++)

                controller.getViewGameInterface().showAnswerFromController(model.getPlayer(j).getName(), "Inizia il turno " + game.getPhase().getRoundNumber());

            while (game.getPhase().getCurrentRound().getRoundState() != FINISHED) {

                //TODO DELETE
                System.out.println("              CHANGE TURN: " + playing.getName());
                LOGGER.log(Level.SEVERE, playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                System.out.println("DraftPool");
                model.getDraftPool().printDraftPool();

                for (int j = 0; j < model.getPlayerList().size(); j++)

                    if (!playing.equals(model.getPlayer(j)))

                        controller.getViewGameInterface().showAnswerFromController(model.getPlayer(j).getName(), "E' il turno di " + playing.getName());

                    else
                        controller.getViewGameInterface().showAnswerFromController(model.getPlayer(j).getName(), "TUO TURNO " + playing.getName());

                ttask1 = timerActionPlayer.scheduleTimerActionPlayer(controller.getSetupHandler(), playing.getName());

                controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                waitEvent();

                if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && !playing.getActionHistory().isFreezed()) {

                    firstAction();

                    if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                        result = false;

                        controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                        waitEvent();

                        if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump())

                            secondAction();
                    }

                } else if (playing.getActionHistory().isFreezed()) {

                    System.out.println("this turn you are freezed");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Salti il turno");
                }

                if (!playing.getActionHistory().isJump() && ttask1 != null) {

                    ttask1.setArrived(true);

                    ttask1.cancelTimerTask();
                }

                timerActionPlayer.resetTimer();

                playerEnding = playing;

                game.getPhase().getCurrentRound().endAction();

                playing = game.getPhase().getCurrentRound().nextPlayer();

                model.hasChanged();

                controller.getViewGameInterface().showSetPlayerMenu(playerEnding.getName(), playerEnding);

                result = false;
            }

            System.out.println("end turn " + i);

            model.getRoundTrackInt().dump();

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);

            i++;
        }

        game.getPhase().doAction(game);

        controller.declareScoresAndWinner(game.getPhase().getWinner());
    }

    public void firstAction() {

        System.out.println("        FIRST ACTION ");

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                if (controller.handler(event)) {

                    System.out.println("done");

                    if (event.getId() == 11)

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Passi il turno ");

                    else if (model.getRestrictions().getColor() != null) //from card 11

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Hai estratto un dado " + model.getRestrictions().getColor());

                    else controller.getViewGameInterface().showAnswerFromController(playing.getName(), "OK");

                    playing.getPlayerBoard().printCard();

                    System.out.println("DraftPool");

                    model.getDraftPool().printDraftPool();

                    model.hasChanged();

                    result = true;
                } else {
                    System.out.println("match error 1 ");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Errore: vuoi riprovare?");

                    controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                    waitEvent();
                }

            } else result = true;
        }
    }

    public void secondAction() {

        System.out.println("        SECOND ACTION ");

        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                if (controller.handler(event)) {

                    System.out.println("done");

                    if (event.getId() == 11)

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Passi ");

                    else controller.getViewGameInterface().showAnswerFromController(playing.getName(), "OK");

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

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Ok, scegli un altro dado");

                        model.hasChanged();

                        controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                        waitEvent();

                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);
                    } else {
                        model.hasChanged();

                        result = true;
                    }
                } else {
                    System.out.println("match error 2 ");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Errore: vuoi riprovare?");

                    controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                    waitEvent();

                    if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);
                }
            } else result = true;
        }
    }

    public void waitEvent() {

        waitCorrectPlayer();

        while (event != null && event.getId() == 12) {

            System.out.println("        SHOWING MENU to player " + event.getPlayer());

            controller.handler(event);

            waitCorrectPlayer();
        }
    }

    public void waitCorrectPlayer() {

        if (playing.getActionHistory().isJump()) System.out.println("ERROR: you don't need to wait an event ");

        LOGGER.log(Level.WARNING, "Event match: " + event);

        do {

            event = controller.getActionEvent();

        } while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump());

        while (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && event!= null && event.getPlayer() != playing.getIDPlayer() && event.getId() != 12) {

            System.out.println("THIS IS NOT THE RIGHT PLAYER: EVENT REFUSED ");

            do {

                event = controller.getActionEvent();

            } while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump());
        }

        if (playing.getActionHistory().isJump()) {

            System.out.println("STOP WAITING because the player end his time");

            controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Your time ended.");
        }

        if (playing.getPlayerState() == STANDBY) System.out.println("STOP WAITING because the player went in STANDBY");
    }

    public PhaseInt getGame() {

        return game.getPhase();
    }

    public TimerTaskActionEvent getTimerTaskActionEvent() {

        return ttask1;
    }

    public TimerActionPlayer getTimerActionPlayer() {

        return timerActionPlayer;
    }
}