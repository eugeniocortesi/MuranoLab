package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.PlayArea.RoundTrackInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class FinalPhase implements PhaseInt {

    private ArrayList<PlayerZone> winners= new ArrayList<PlayerZone>();

    private int maximum;
    private int minimum;


    public PlayerZone declareWinner(ArrayList<PlayerZone> players, RoundTrackInt roundTrack) throws IllegalArgumentException{
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
                for(PlayerZone j : winners){
                    if(j.getPrivatePoints()!=maximum) winners.remove(j);
                }
                if(winners.size()==1) return winners.get(0);
                else{
                    maximum=-1;
                    for(PlayerZone i : winners){
                        if(i.getToken().getTokenNumber()>maximum){
                           maximum = i.getToken().getTokenNumber();
                        }
                    }
                    for(PlayerZone j : winners){
                        if(j.getToken().getTokenNumber()!=maximum) winners.remove(j);
                    }
                    if(winners.size()==1) return winners.get(0);
                    else {
                        minimum=-1;
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

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

    }
}
