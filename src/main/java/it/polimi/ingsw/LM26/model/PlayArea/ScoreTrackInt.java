package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.io.Serializable;

public abstract class ScoreTrackInt implements Serializable{

    String typeScoreTrack;

    public abstract int getScoreTrack(ScoreMarker sm);

    public abstract void rewrite();
}
