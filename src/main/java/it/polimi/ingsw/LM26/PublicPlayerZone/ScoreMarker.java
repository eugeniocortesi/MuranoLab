package it.polimi.ingsw.LM26.PublicPlayerZone;

import it.polimi.ingsw.LM26.Cards.Color;

import java.io.FileReader;

public class ScoreMarker {

    public Color color;

    public int points = 0;

    public boolean more50 = false;

    public ScoreMarker(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public void incrementScore(int increment){
        points=points + increment;
        }

        public void flipScoreMarker(){
        this.more50=true;
        }
}
