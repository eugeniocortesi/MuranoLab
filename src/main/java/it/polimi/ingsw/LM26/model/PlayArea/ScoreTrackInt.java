package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.io.Serializable;

/**
 * ScoreTrack abstract class
 * @author Eugenio Cortesi
 */

public abstract class ScoreTrackInt implements Serializable{

    String typeScoreTrack;

    public abstract void rewrite();

    public abstract  void addToScoreTrack(ScoreMarker scoreMarker);
}
