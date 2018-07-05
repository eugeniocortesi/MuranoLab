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

/**
 * @author Eugenio Cortesi
 * class that for each part of the game gets the corrent players and turns
 */
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

    public RoundsHandler(Model model, ControllerInt controller) {

        LOGGER.setLevel(Level.ALL);

        this.controller = controller;

        this.game = new Game();  //initialPhase

        game.getPhase().doAction(game);    //centralPhase

        this.model = model;

        controller.setGamePhase(game.getPhase());

        this.model.hasChanged();

        TimerImplementation timerImplementation = new TimerImplementation();

        TimerConfiguration timerConfiguration = timerImplementation.implentation();

        timerActionPlayer = new TimerActionPlayer(timerConfiguration.getTimerEnd());
    }

    @Override
    public void run() {

        play();
    }

    /**
     * this method runs the game, sends messages to view and asks to show menu to players
     * it selects the next player from the current round and the next round from the central phase of the game.
     * if the player ends his time for a action, goes to standby or pass the turn, the turn ends.
     * after every action is notify all clients that model has changed.
     */
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
                LOGGER.log(Level.SEVERE, playing.getName() + " is playing ");
                playing.getPlayerBoard().printCard();
                LOGGER.log(Level.INFO, "DraftPool");
                model.getDraftPool().printDraftPool();


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

                    LOGGER.log(Level.INFO, "this turn you are freezed");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Salti il turno");
                }

                if (!playing.getActionHistory().isJump() && ttask1 != null) {

                    ttask1.setArrived(true);

                    ttask1.cancelTimerTask();
                }

                timerActionPlayer.resetTimer();

                PlayerZone playerEnding = playing;

                game.getPhase().getCurrentRound().endAction();

                playing = game.getPhase().getCurrentRound().nextPlayer();

                model.hasChanged();

                //TODO DELETE
                for (int j = 0; j < model.getPlayerList().size(); j++)
                    if (!playing.equals(model.getPlayer(j)))
                        controller.getViewGameInterface().showAnswerFromController(model.getPlayer(j).getName(), "E' il turno di " + playing.getName());

                    else
                        controller.getViewGameInterface().showAnswerFromController(model.getPlayer(j).getName(), "TUO TURNO " + playing.getName());

                controller.getViewGameInterface().showSetPlayerMenu(playerEnding.getName(), playerEnding);

                result = false;
            }

            game.getPhase().nextRound(game.getPhase().getCurrentRound(), game);

            i++;
        }

        game.getPhase().doAction(game);

        controller.declareScoresAndWinner(game.getPhase().getWinner());
    }

    /**
     * this method receive the event for the first action and give it to the handler
     * when the handler returns the boolean true/false, the method communicates to client the answer
     * and if it's negative, it asks to client to redo the action trough menu showing
     */
    private void firstAction() {

        //TODO DELETE
        LOGGER.log(Level.INFO, "        FIRST ACTION ");


        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                if (controller.handler(event)) {

                    LOGGER.log(Level.INFO, "done");

                    if (event.getId() == 11)

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Passi il turno ");

                    else if (model.getRestrictions().getColor() != null)  //from card 11

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Hai estratto un dado " + model.getRestrictions().getColor());

                    else controller.getViewGameInterface().showAnswerFromController(playing.getName(), "OK");

                    //TODO DELETE
                    playing.getPlayerBoard().printCard();
                    LOGGER.log(Level.INFO, "DraftPool");
                    model.getDraftPool().printDraftPool();


                    model.hasChanged();

                    result = true;
                } else {
                    LOGGER.log(Level.INFO, "match error 1 ");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Errore: vuoi riprovare?");

                    controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                    waitEvent();
                }

            } else result = true;
        }
    }

    /**
     * this method receive the event for the second action (if the client didn't pass the turn) and give it to the handler
     * when the handler returns the boolean true/false, the method communicates to client the answer
     * * and if it's negative, it asks to client to redo the action trough menu showing.
     * if ToolCard 8 has been used by the client, the method asks for one more action.
     */
    private void secondAction() {

        //TODO DELETE
        LOGGER.log(Level.INFO, "        SECOND ACTION ");


        while (!result) {

            if (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump()) {

                if (controller.handler(event)) {

                    LOGGER.log(Level.INFO, "done");

                    if (event.getId() == 11)

                        controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Passi ");

                    else controller.getViewGameInterface().showAnswerFromController(playing.getName(), "OK");


                    //TODO DELETE
                    LOGGER.log(Level.INFO, "DraftPool");
                    model.getDraftPool().printDraftPool();


                    if (model.getRestrictions().isTool8needPlacement()) {

                        if (playing.getActionHistory().isJump()) playing.getActionHistory().setJump(false);

                        playing.getActionHistory().setPlacement(false);

                        playing.getActionHistory().setDieUsed(false);

                        model.getRestrictions().setCurrentPlacement(true);

                        playing.getActionHistory().setFreezed(true);

                        LOGGER.log(Level.INFO, "choose another die");

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
                    LOGGER.log(Level.INFO, "match error 2 ");

                    controller.getViewGameInterface().showAnswerFromController(playing.getName(), "Errore: vuoi riprovare?");

                    controller.getViewGameInterface().showSetPlayerMenu(playing.getName(), playing);

                    waitEvent();

                    if (playing.getActionHistory().isJump()) playing.getActionHistory().setFreezed(false);
                }
            } else result = true;
        }
    }

    /**
     * this method makes sure that every time a "ask menu event" has arrived from the client
     * it handles it and wait an other event right away
     */
    private void waitEvent() {

        waitCorrectPlayer();

        while (event != null && event.getId() == 12) {

            controller.handler(event);

            waitCorrectPlayer();
        }
    }

    /**
     * the scope of this method is to get an event from the queue,
     * if the queue is empty, it waits for a new event to arrive
     * and refuse an event if the client sending it is not the player that is playing this round.
     * Numerous controls are necessary:
     * for instance if a player goes to standby while the method is waiting
     * or the timer for the action ends, the method exits the while and ends.
     */
    private void waitCorrectPlayer() {

        do {

            event = controller.getActionEvent();

        } while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump());

        while (playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump() && event != null && event.getPlayer() != playing.getIDPlayer() && event.getId() != 12) {

            do {

                event = controller.getActionEvent();

            } while (event == null && playing.getPlayerState() != STANDBY && !playing.getActionHistory().isJump());
        }
    }

    public PhaseInt getGame() {

        return game.getPhase();
    }

    public TimerActionPlayer getTimerActionPlayer() {

        return timerActionPlayer;
    }

    public TimerTaskActionEvent getTimerTaskActionEvent() {

        return ttask1;
    }
}