package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestScoreTrack {

    ScoreTrack st = new ScoreTrack("s");

    @Test
    public void checkScoreTrack(){

     ScoreMarker sc = new ScoreMarker(Color.ANSI_RED, new PlayerZone("name1", 0));
     ScoreMarker sc1 = new ScoreMarker(Color.ANSI_RED, new PlayerZone("name2", 1));
     sc.incrementScore(73);
     sc1.incrementScore(40);
     st.addToScoreTrack(sc);
     st.addToScoreTrack(sc1);
     assertEquals(st.getScoreTrackCell(23), sc);
     assertEquals(st.getScoreTrackCell(40),sc1 );
 }
}