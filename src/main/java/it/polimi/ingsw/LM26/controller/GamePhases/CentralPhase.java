package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

/**
 * Class CentralPhase
 * @author EugenioCortesi
 * class that keeps track of round-situation and moves game toward finalPhase
 */

public class CentralPhase implements PhaseInt {

    private Model model;

    private static int numRounds = 10;

    private ArrayList<Round> rounds;

    private transient Round round;

    private Boolean onePlayer = false;

    private PlayerZone last = null;

    private int[] turn;

    private static final Logger LOGGER = Logger.getLogger(CentralPhase.class.getName());

    /**
     * Constructor
     * it sets the first players order and start the first round
     */

    public CentralPhase() {

        this.model = singletonModel();

        LOGGER.setLevel(Level.ALL);

        turn = setOrder(model.getPlayerList().size());

        this.round = new Round(turn);

        rounds = new ArrayList<>();

        rounds.add(round);
    }


    /**
     * it sets the players order (es: 123321); every player plays one time, then another in the revers order
     * @param nplayers playing
     * @return the vector with the updated players order to use in the next round
     */

    private int[] setOrder(int nplayers) {

        turn = new int[2 * nplayers];

        for (int i = 0; i < nplayers; i++) turn[i] = model.getPlayer(i).getNumber();

        for (int i = nplayers, j = nplayers - 1; j >= 0; i++, j--) turn[i] = model.getPlayer(j).getNumber();

        return turn;
    }


    /**
     * the first player in the sequence of playing is now the last (es: 1234 becomes 2341)
     * the method calls again setOrder to update the order vector with the changed players sequence
     * @param nplayers playing
     */

    public void resetOrder(int nplayers) {

        int last = model.getPlayer(nplayers - 1).getNumber();

        model.getPlayer(nplayers - 1).setNumberPlayer(model.getPlayer(0).getNumber());

        for (int i = 0; i < model.getPlayerList().size() - 1; i++)

            model.getPlayer(i).setNumberPlayer(model.getPlayer(i + 1).getNumber());

        model.getPlayer(nplayers - 2).setNumberPlayer(last);

        turn = setOrder(nplayers);
    }


    /**
     * every time a round ends this method is called.
     * it creates a new round if the current wasn't the 9th, otherwise if round 9th ended
     * or if only one player remains in the game, the method creates the finalPhase
     * @param round round that just ended
     * @param game  instance of game, important to get the right game-phase
     */

    @Override
    public void nextRound(Round round, Game game) {

        if (rounds.size() == numRounds || onePlayer) {

            LOGGER.log(Level.INFO, "CentralPhase: creating finalPhase ");

            game.setPhase(new FinalPhase());

            if (onePlayer)game.getPhase().endGame(last);
        } else

            if (rounds.size() < numRounds && round.getRoundState() == RoundState.FINISHED) {

            resetOrder(model.getPlayerList().size());

            this.round = new Round(turn);

            rounds.add(round);
        }
    }


    /**
     * the method stores the last player not in standby, so that if he goes to standby before of the points computation,
     * he will be classified as winner even if he has already disconnected.
     * @param last last player not in standby
     */

    @Override
    public void endGame(PlayerZone last) {

        LOGGER.log(Level.INFO, "Only one player remained");

        onePlayer = true;

        this.last=last;
    }

    @Override
    public int[] getTurn() {

        return turn;
    }


    @Override
    public int getNrounds() {

        return numRounds;
    }

    @Override
    public boolean getOnePlayer() {

        return onePlayer;
    }

    @Override
    public int getRoundNumber() {

        return rounds.size();
    }

    @Override
    public Round getCurrentRound() {

        return round;
    }

    @Override
    public PlayerZone getWinner() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void doAction(Game game) {

        throw new UnsupportedOperationException("Not supported here");
    }
}
