package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.PlayArea.RoundTrackInt;
import it.polimi.ingsw.LM26.PlayArea.ScoreTrack;
import it.polimi.ingsw.LM26.PlayArea.ScoreTrackInt;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

public class FinalPhase implements PhaseInt {

    private ArrayList<PlayerZone> winners= new ArrayList<PlayerZone>();

    private int maximum;

    private int privateMaximum=-1;

    private int tokenMaximum=-1;

    public PlayerZone declareWinner(ArrayList<PlayerZone> players, RoundTrackInt roundTrack){
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
                for(PlayerZone i : winners){
                    if(i.getPrivatePoints() > privateMaximum) {
                        privateMaximum = i.getPrivatePoints();
                    }
                }
                for(PlayerZone j : winners){
                    if(j.getPrivatePoints()!=privateMaximum) winners.remove(j);
                }
                if(winners.size()==1) return winners.get(0);
                else{
                    for(PlayerZone i : winners){
                        if(i.getToken().getTokenNumber()>tokenMaximum){
                            tokenMaximum = i.getToken().getTokenNumber();
                        }
                    }
                    for(PlayerZone j : winners){
                        if(j.getToken().getTokenNumber()!=tokenMaximum) winners.remove(j);
                    }
                    if(winners.size()==1) return winners.get(0);
                    else return roundTrack.getLast();
                }
            }
        }
    }

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

    }
}
