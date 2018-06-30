package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class FinalPhase implements PhaseInt {

    private ArrayList<PlayerZone> winners= new ArrayList<PlayerZone>();

    private int maximum;

    private int minimum;

    private PlayerZone winner;


    /**
     * the method declares the winner: he's the one connected
     * if there is only one player; else "The player with the highest Victory Point
     * total is the winner. Ties are broken by most
     * points from Private Objectives, most remaining
     * Favor Tokens, then finally by reverse player
     * order in the final round."
     * @param pls, the list of all players enrolled
     * @return the winner
     * @throws IllegalArgumentException when there are problems with players' LastTurnValue
     */
    public PlayerZone declareWinner(ArrayList<PlayerZone> pls) throws IllegalArgumentException{

        ArrayList<PlayerZone> players = new ArrayList<PlayerZone>();
        for(int i=0; i<pls.size(); i++) {
            if (pls.get(i).getPlayerState() != PlayerState.STANDBY)
                players.add(pls.get(i));
        }

        if(players.size()==1) return players.get(0);
        else{
            maximum=players.get(0).getScoreMarker().getRealPoints();
            for(PlayerZone i : players) {
                if (i.getScoreMarker().getRealPoints() > maximum) {
                    maximum = i.getScoreMarker().getRealPoints();
                }
            }
            for(PlayerZone j : players){
                if(j.getScoreMarker().getRealPoints()==maximum) winners.add(j);
            }
            if(winners.size()==1) return winners.get(0);
            else{
                maximum=-1;
                for(PlayerZone i : winners){
                    if(i.getPrivatePoints() > maximum) {
                        maximum = i.getPrivatePoints();
                    }
                }
                for(int j=0; j<winners.size();){
                    if(winners.get(j).getPrivatePoints()!=maximum) winners.remove(j);
                    else j++;
                }
                if(winners.size()==1) return winners.get(0);
                else{
                    maximum=-1;
                    for(PlayerZone i : winners){
                        if(i.getToken().getTokenNumber()>maximum){
                            maximum = i.getToken().getTokenNumber();
                        }
                    }
                    for(int j=0; j<winners.size();){
                        if(winners.get(j).getToken().getTokenNumber()!=maximum) winners.remove(j);
                        else j++;
                    }
                    if(winners.size()==1) return winners.get(0);
                    else {
                        minimum=10;
                        for(PlayerZone i : winners) {
                            if (i.getLastRoundTurn() < minimum) {
                                minimum = i.getLastRoundTurn();
                            }
                        }
                        for(PlayerZone j : winners){
                            if(j.getLastRoundTurn() == minimum) return j;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException("players have no LastRoundTurn value");
    }

    public void computeScore() {

        Model model = singletonModel();


        int result = 0;

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            PlayerZone player = model.getPlayerList().get(i);
            for (int j = 0; j < model.getDecks().getObjectivePrivateCardDeck().size(); j++) {
                ObjectivePrivateCard card = model.getDecks().getObjectivePrivateCardDeck().get(j);
                if (card.getPlayer() != null && card.getPlayer().equals(player)) {
                    result = card.checkPoints(player.getPlayerBoard());
                    System.out.println("points from private card " + card.getColour() + " " +  result);
                    player.setPrivatePoints(result);
                }

            }
            for (int j = 0; j < model.getOnBoardCards().getObjectivePublicCardList().size(); j++) {
                int fromPublic = model.getOnBoardCards().getObjectivePublicCardList().get(j).computePoints(player);
                result = result + fromPublic;
                System.out.println("points from public card (" + model.getOnBoardCards().getObjectivePublicCardList().get(j).getEffect() + ") : " + fromPublic);
            }
            result = result + player.getToken().getTokenNumber();
            System.out.println("points from tokens " + player.getToken().getTokenNumber());
            int emptysp= player.getPlayerBoard().getEmptySpaces();
            result = result - emptysp;
            System.out.println("with " + emptysp + " emptyspaces");
            player.getScoreMarker().incrementScore(result);
            //TODO
            //model.getScoreTrackInt().addToScoreTrack(player.getScoreMarker());

        }
    }

    public PlayerZone getWinner() {

        return winner;
    }

    @Override
    public void setAllInStandBy(Boolean allInStandBy) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

        computeScore();

        winner = declareWinner(playerList);

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
