package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.util.ArrayList;

public class ScoreTrack extends ScoreTrackInt {

    private ScoreMarker[] scoreMarkerList;

    public ScoreTrack(){

        scoreMarkerList = new ScoreMarker[50];

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
