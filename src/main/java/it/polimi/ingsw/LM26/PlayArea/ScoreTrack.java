package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.PublicPlayerZone.ScoreMarker;

import java.util.ArrayList;

public class ScoreTrack implements ScoreTrackInt {

    private ArrayList<ScoreMarker> scoreMarkerList;
    
    private PlayerZone last=null;

    public ScoreTrack(){

        scoreMarkerList = new ArrayList<ScoreMarker>();

    }

    public PlayerZone getLast() {
        return last;
    }

    public void setLast(PlayerZone last) {
        this.last = last;
    }

    public int getScoreTrack(ScoreMarker sm) {
        return sm.getRealPoints();
    }
}
