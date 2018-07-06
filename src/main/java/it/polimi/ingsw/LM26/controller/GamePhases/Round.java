package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.controller.controllerHandler.UpdatesHandler;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Eugenio Cortesi
 */

public class Round {

    private Model model;

    private int turnCounter = 0;

    private PlayerZone currentPlayer;

    private RoundState roundState = RoundState.RUNNING;

    private int[] turn;

    private static final Logger LOGGER = Logger.getLogger(Round.class.getName());


    /**
     * Constructor
     * it sets up things for this new turn like draft pool and players direction
     * @param turn new players order to use in the current round
     */

    Round(int[] turn) {

        this.model = singletonModel();

        LOGGER.setLevel(Level.ALL);

        this.turn = turn;

        pullDice();

        setPlayerDirection();
    }


    /**
     * this method is called after endAction() in RoundsHandler class.
     * it searches the player with the number in the turn vector at the current turnCounter
     * if the player is in standby the turnCounter is incremented and the method searches again for the player with the new selected number.
     * if during the research the it arrives to the end of the turns vector, it ends the turn and return no player.
     * @return the next playing player
     */

    public PlayerZone nextPlayer()  {

        ArrayList<PlayerZone> player = model.getPlayerList();

        for (PlayerZone aPlayer : player) {

            if ((aPlayer.getIDPlayer() + 1) == turn[turnCounter]) {

                if (aPlayer.getPlayerState() != STANDBY) {

                    aPlayer.setPlayerState(PlayerState.BEGINNING);

                    currentPlayer = aPlayer;

                    return aPlayer;
                }

                else { LOGGER.log(Level.INFO,aPlayer.getName() + " is in STANDBY");

                    turnCounter = turnCounter + 1;

                    if (turnCounter == turn.length) {

                        endRound();

                        currentPlayer = null;

                        return null;
                    } else return nextPlayer();
                }
            }
        }

        throw new IllegalArgumentException("no player has the current turn");
    }


    /**
     * it sets the player parameters associated to action-done, and if there aren't any more players in the turn, it ends it.
     */

    public void endAction() {

        setPlayerDirection();

        if (currentPlayer.getPlayerState() != STANDBY)

            currentPlayer.setPlayerState(PlayerState.ENDING);

        currentPlayer.getActionHistory().deleteTurnHistory();

        model.getRestrictions().resetRestrictions();

        turnCounter++;

        if (turnCounter == turn.length) {

            endRound();

        } else roundState = RoundState.RUNNING;
    }


    /**
     * method that sets turn and players parameters associated to turn-done, then it moves the remained dice from the draft pool to the roundTrack
     */

    private void endRound() {

        LOGGER.log(Level.INFO," TURN ENDED ");

        model.getRoundTrackInt().addDice(model.getDraftPool().getInDraft());

        model.getRoundTrackInt().update();

        model.getDraftPool().removeAllDice();

        currentPlayer.getActionHistory().deleteRoundHistory();

        turnCounter = 0;

        roundState = RoundState.FINISHED;
    }


    /**
     * this method set the boolean corresponding to the occurrence of the player in the turn:
     * it sets first turn to true if the direction in right (players are going to do their first turn) and second to false,
     * then second turn to true and first to false if the direction, so they have done their first turn and are going to do the second.
     */

    private void setPlayerDirection() {

        if (turnCounter == 0)

            for (int i = 0; i < model.getPlayerList().size(); i++) {

                model.getPlayer(i).getActionHistory().setFirstTurn(true);

                model.getPlayer(i).getActionHistory().setSecondTurn(false);
            }

        else if (turnCounter < turn.length - 1 && turn[turnCounter] == turn[turnCounter + 1])

            for (int i = 0; i < model.getPlayerList().size(); i++) {

                model.getPlayer(i).getActionHistory().setFirstTurn(false);

                model.getPlayer(i).getActionHistory().setSecondTurn(true);
            }

    }


    /**
     * at the beginning of the turn, the draft pool il empty and must be filled with two dice per player plus one for the round track;
     * no dice for players in standby
     */

    private void pullDice() {

        int contStandby = 0;

        int contDice;

        DieInt die;

        for (int j = 0; j < model.getPlayerList().size(); j++)

            if (model.getPlayerList().get(j).getPlayerState() == STANDBY)

                contStandby = contStandby + 1;

        contDice = model.getPlayerList().size() - contStandby;

        for (int i = 0; i < contDice; i++) {

            die = model.getBag().draw();

            die.roll();

            model.getDraftPool().addDie(die);

            die = model.getBag().draw();

            die.roll();

            model.getDraftPool().addDie(die);
        }

        die = model.getBag().draw();

        die.roll();

        model.getDraftPool().addDie(die);
    }

    public RoundState getRoundState() {

        return roundState;
    }

    public int getTurnCounter() {

        return turnCounter;
    }
}