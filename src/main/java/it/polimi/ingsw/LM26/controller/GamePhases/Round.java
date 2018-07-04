package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState.STANDBY;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Round {

    private Model model;

    //private static final int[] t = {1, 2, 3, 4};

    private int turnCounter = 0;

    private PlayerZone currentPlayer;

    private RoundState roundState = RoundState.RUNNING;

    private int[] turn;

    public Round(int[] turn) {

        this.model = singletonModel();

        this.turn = turn;

        //this.assignTurn(rTrack, pZone, nrounds);

        pullDice();
    }

    //assegna l'ordine con cui si gioca all'interno di un turno e salva l'ordine dell'ultimo turno per la parità di punteggio
    //roundtrack passata dalla centralPhase, l'nrounds da passare è la costante della centralPhase

    /*public void assignTurn(RoundTrackInt roundTrack, ArrayList<PlayerZone> playerList, int nrounds) {
        for (int i = 0; i < playerList.size(); i++) {

            int j=t[(i ) %playerList.size()];

            playerList.get(i).setNumberPlayer(j);

            playerList.get(i).setLastRoundTurn(j);
        }
    }
    */

    //nextPlayer va usato dopo endAction, quando il contatore è già incrementato. plStandby passato è sempre 0
    public PlayerZone nextPlayer() throws IllegalArgumentException {

        ArrayList<PlayerZone> player = model.getPlayerList();

        for (int i = 0; i < player.size(); i++) {

            if ((player.get(i).getIDPlayer() + 1) == turn[turnCounter]) {

                if (player.get(i).getPlayerState() != STANDBY) {

                    player.get(i).setPlayerState(PlayerState.BEGINNING);

                    if (turnCounter > 0) {

                        if (turn[turnCounter] == turn[turnCounter - 1]) {

                            player.get(i).getActionHistory().setFirstTurn(false);

                            player.get(i).getActionHistory().setSecondTurn(true);

                        } else player.get(i).getActionHistory().setFirstTurn(true);

                    } else player.get(i).getActionHistory().setFirstTurn(true);

                    currentPlayer = player.get(i);

                    return player.get(i);
                } else {
                    System.out.println(player.get(i).getName() + " is in STANDBY");

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

    //passa il turno al successivo, se è finito turno globale mette dadi nella casella della round track e ritorna FINISHED
    public void endAction() {

        if (currentPlayer.getPlayerState() != STANDBY)

            currentPlayer.setPlayerState(PlayerState.ENDING);

        currentPlayer.getActionHistory().deleteTurnHistory();

        model.getRestrictions().resetRestrictions();

        turnCounter++;

        if (turnCounter == turn.length) {

            endRound();

        } else roundState = RoundState.RUNNING;
    }

    public void endRound() {

        System.out.println(" TURN ENDED ");

        model.getRoundTrackInt().addDice(model.getDraftPool().getInDraft());

        model.getRoundTrackInt().update();

        model.getDraftPool().removeAllDice();

        currentPlayer.getActionHistory().deleteRoundHistory();

        turnCounter = 0;

        roundState = RoundState.FINISHED;
    }

    public void pullDice() {

        int contStandby = 0;

        int contDice = 0;

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