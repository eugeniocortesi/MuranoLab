package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;

import java.io.Serializable;

public interface ScoreTrackInt extends Serializable{
    public int getScoreTrack(ScoreMarker sm);

    public void rewrite();
}
