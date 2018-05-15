package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

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
