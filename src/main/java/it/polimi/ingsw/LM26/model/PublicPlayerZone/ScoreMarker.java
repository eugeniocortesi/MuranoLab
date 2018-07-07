package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

/**
 * ScoreMarker class
 * @author Eugenio Cortesi
 * each score marcker object is associated to a client
 */

public class ScoreMarker implements Serializable {

    private Color color;

    private int points = 0;

    private boolean more50 = false;

    public ScoreMarker() {
    }

    public boolean isMore50() {

        return more50;
    }

    public ScoreMarker(Color color, PlayerZone player) {

        this.color = color;
    }

    public Color getColor() {

        return color;
    }

    /**
     * actual points, not limited to the score track structure
     * @return
     */

    public int getRealPoints() {

        if (more50) return points + 50;

        return points;
    }

    /**
     * points can be negative, if they are the player has = points
     * @return points starting from 0 to 50
     */

    public int getPoints() {

        if (points > 0) return points;

        else return 0;
    }

    /**
     * method adds the client points to the score marker, but refactors the information to be coherent with the score track structure
     * if actual points are more than 50, it flips the score-marker-parameter
     * @param increment clients points
     */
    public void incrementScore(int increment) {

        if (this.points + increment < 50)

            this.points = this.points + increment;

        else {

            this.points = this.points + increment - 50;

            flipScoreMarker();
        }
    }

    private void flipScoreMarker() {

        this.more50 = true;
    }
}
