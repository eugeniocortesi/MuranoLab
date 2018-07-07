package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

/**
 * ScoreTrack class
 * @author Eugenio Cortesi
 */

public class ScoreTrack extends ScoreTrackInt {

    private ScoreMarker[] scoreMarkerList;

    public ScoreTrack(){
    }

    public ScoreTrack(String s){

        final int dimension=50;

        scoreMarkerList = new ScoreMarker[dimension];

        this.typeScoreTrack="ScoreTrack";
    }

    public ScoreMarker getScoreTrackCell(int n) {

        return scoreMarkerList[n];
    }

    public void addToScoreTrack(ScoreMarker scoreMarker) {

        scoreMarkerList[scoreMarker.getPoints()]=scoreMarker;
    }

    @Override
    public void rewrite() {

        this.typeScoreTrack="ScoreTrack";
    }
}
