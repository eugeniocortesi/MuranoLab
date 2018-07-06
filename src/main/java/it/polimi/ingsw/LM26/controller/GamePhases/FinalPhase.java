package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;


/**
 * FinalPhase class
 * @author Eugenio Cortesi
 */

public class FinalPhase implements PhaseInt {

    private Model model = singletonModel();

    private PlayerZone winner;

    private static final Logger LOGGER = Logger.getLogger(FinalPhase.class.getName());

    FinalPhase() {

        LOGGER.setLevel(Level.ALL);
    }


    /**
     * the method declares the winner: he's the one connected if there is only one player;
     * else "The player with the highest Victory Point
     * total is the winner. Ties are broken by most
     * points from Private Objectives, most remaining
     * Favor Tokens, then finally by reverse player
     * order in the final round."
     * @return the winner
     * @throws IllegalArgumentException when there are problems with players' LastTurnValue
     */

    public PlayerZone declareWinner() {

        ArrayList<PlayerZone> players = new ArrayList<PlayerZone>();

        ArrayList<PlayerZone> potentialWinners = new ArrayList<PlayerZone>();

        int maximum;

        int minimum;

        for(int i=0; i<model.getPlayerList().size(); i++) {

            if (model.getPlayerList().get(i).getPlayerState() != PlayerState.STANDBY)

                players.add(model.getPlayerList().get(i));
        }

        if(players.size()==1) return players.get(0);

        else{ maximum=players.get(0).getScoreMarker().getRealPoints();

            for(PlayerZone i : players) {

                if (i.getScoreMarker().getRealPoints() > maximum) {

                    maximum = i.getScoreMarker().getRealPoints();
                }
            }

            for(PlayerZone j : players){

                if(j.getScoreMarker().getRealPoints()==maximum)

                    potentialWinners.add(j);
            }

            if(potentialWinners.size()==1) return potentialWinners.get(0);

            else{ maximum=-1;

                for(PlayerZone i : potentialWinners){

                    if(i.getPrivatePoints() > maximum) {

                        maximum = i.getPrivatePoints();
                    }
                }

                for(int j=0; j< potentialWinners.size();){

                    if(potentialWinners.get(j).getPrivatePoints()!=maximum)

                        potentialWinners.remove(j);

                    else j++;
                }

                if(potentialWinners.size()==1) return potentialWinners.get(0);

                else{ maximum=-1;

                    for(PlayerZone i : potentialWinners){

                        if(i.getToken().getTokenNumber()>maximum){

                            maximum = i.getToken().getTokenNumber();
                        }
                    }

                    for(int j=0; j< potentialWinners.size();){

                        if(potentialWinners.get(j).getToken().getTokenNumber()!=maximum)

                            potentialWinners.remove(j);

                        else j++;
                    }

                    if(potentialWinners.size()==1) return potentialWinners.get(0);

                    else {

                        minimum=10;

                        for(PlayerZone i : potentialWinners) {

                            if (i.getNumber() < minimum) {

                                minimum = i.getNumber();
                            }
                        }

                        for(PlayerZone j : potentialWinners){

                            if(j.getNumber() == minimum)

                                return j;
                        }
                    }
                }
            }
        }

        throw new IllegalArgumentException("players have no LastRoundTurn value");
    }


    /**
     * method computes the scores gained by every player from private cards, public cards, tokens remained and empty spaces on the player's board
     */

    public void computeScore() {

        int result = 0;

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            PlayerZone player = model.getPlayerList().get(i);

            for (int j = 0; j < model.getDecks().getObjectivePrivateCardDeck().size(); j++) {

                ObjectivePrivateCard card = model.getDecks().getObjectivePrivateCardDeck().get(j);

                if (card.getPlayer() != null && card.getPlayer().equals(player)) {

                    result = card.checkPoints(player.getPlayerBoard());

                    LOGGER.log(Level.INFO, "points from private card " + card.getColour() + " " +  result);

                    player.setPrivatePoints(result);
                }
            }

            for (int j = 0; j < model.getOnBoardCards().getObjectivePublicCardList().size(); j++) {

                int fromPublic = model.getOnBoardCards().getObjectivePublicCardList().get(j).computePoints(player);

                result = result + fromPublic;

                LOGGER.log(Level.INFO,"points from public card (" + model.getOnBoardCards().getObjectivePublicCardList().get(j).getEffect() + ") : " + fromPublic);
            }

            result = result + player.getToken().getTokenNumber();

            LOGGER.log(Level.INFO,"points from tokens " + player.getToken().getTokenNumber());

            int emptysp= player.getPlayerBoard().getEmptySpaces();

            result = result - emptysp;

            LOGGER.log(Level.INFO,"with " + emptysp + " emptyspaces");

            player.getScoreMarker().incrementScore(result);

            if(result>0)

                model.getScoreTrackInt().addToScoreTrack(player.getScoreMarker());

            model.hasChanged();
        }
    }


    /**
     * method is launched from RoundsHandler and it calls the class methods to declare points and winner
     * @param game instance of game
     */

    @Override
    public void doAction(Game game) {

        computeScore();

        winner = declareWinner();
    }

    public PlayerZone getWinner() {

        return winner;
    }

    @Override
    public int getNrounds() {

        return 10;
    }

    @Override
    public boolean getOnePlayer() {

        return true;
    }

    @Override
    public int getRoundNumber() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void endGame() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void nextRound(Round round, Game game) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public Round getCurrentRound() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public int[] getTurn() {

        throw new UnsupportedOperationException("Not supported here");
    }
}
