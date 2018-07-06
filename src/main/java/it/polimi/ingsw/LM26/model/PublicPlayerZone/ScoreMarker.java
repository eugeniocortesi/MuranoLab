package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

public class ScoreMarker implements Serializable {

    private Color color;

    private int points = 0;
    //not all points if more50 is true
    //in this case points = points + 50;

    private boolean more50 = false;

    public boolean isMore50() {
        return more50;
    }

    public ScoreMarker(){
    }

    public ScoreMarker(Color color, PlayerZone player) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getRealPoints() {

        if (more50) {

            return points + 50;
        }
        return points;
    }

    public int getPoints(){

        if( points >0) return points;

        else return 0;
    }

    public void incrementScore(int increment) {
        if (this.points + increment < 50){
            this.points = this.points + increment;
    }
        else

    {

        this.points = this.points + increment - 50;
        flipScoreMarker();
    }

}

    private void flipScoreMarker(){
        this.more50=true;

        }
}
