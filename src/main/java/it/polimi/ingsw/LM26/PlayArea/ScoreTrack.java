package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.PublicPlayerZone.ScoreMarker;

import java.util.ArrayList;

public class ScoreTrack implements ScoreTrackInt {

    private ArrayList<ScoreMarker> scoreMarkerList;

    public ScoreTrack(){

        scoreMarkerList = new ArrayList<ScoreMarker>();

    }

    public int getScoreTrack(ScoreMarker sm) {
        return sm.getRealPoints();
    }
}
