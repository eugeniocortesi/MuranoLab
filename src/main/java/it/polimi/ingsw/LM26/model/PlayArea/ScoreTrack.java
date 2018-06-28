package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.util.ArrayList;

public class ScoreTrack extends ScoreTrackInt {

    private ArrayList<ScoreMarker> scoreMarkerList;

    public ScoreTrack(){

        scoreMarkerList = new ArrayList<ScoreMarker>();
        this.typeScoreTrack="ScoreTrack";

    }

    public int getScoreTrack(ScoreMarker sm) {
        return sm.getRealPoints();
    }

    //TODO
    //create structure, add setter and getter for scoreMarker

    @Override
    public void rewrite() {

        this.typeScoreTrack="ScoreTrack";

    }
}
