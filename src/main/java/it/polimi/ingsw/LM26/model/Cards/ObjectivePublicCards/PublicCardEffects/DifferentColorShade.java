package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;


/**
 * PublicCard effect class
 * @author Eugenio Cortesi
 */

public class DifferentColorShade extends Effect {

    private boolean colorShades;


    /**
     * Constructor
     * @param colorShades color implies false, shade implies true
     */

    public DifferentColorShade(boolean colorShades) {

        this.colorShades = colorShades;

        this.typeEffect = "DifferentColorShade";
    }

    private String iscolorShades() {

        if (!colorShades) return "Colors";

        else return "Shades";
    }


    /**
     * method is implemented both to search for color and shade
     * @param b board on which calculate points
     * @return points gained with this objective
     */

    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();

        int[] num = new int[6];

        int min;

        final int row=4;

        final int column=5;

        if (!colorShades) {

            num[5] = 18;

            min = 18;

            for (int i = 0; i < row; i++)

                for (int j = 0; j < column; j++)

                    if (board[i][j].isIsPresent()) {

                        if (board[i][j].getDie().getColor().equals(Color.ANSI_GREEN)) num[0]++;

                        if (board[i][j].getDie().getColor().equals(Color.ANSI_RED)) num[1]++;

                        if (board[i][j].getDie().getColor().equals(Color.ANSI_PURPLE)) num[2]++;

                        if (board[i][j].getDie().getColor().equals(Color.ANSI_BLUE)) num[3]++;

                        if (board[i][j].getDie().getColor().equals(Color.ANSI_YELLOW)) num[4]++;
                    }
        } else {

            min = 20;

            for (int i = 0; i < row; i++)

                for (int j = 0; j < column; j++)

                    if (board[i][j].isIsPresent()) {

                        if (board[i][j].getDie().getValue() == 1) num[0]++;

                        if (board[i][j].getDie().getValue() == 2) num[1]++;

                        if (board[i][j].getDie().getValue() == 3) num[2]++;

                        if (board[i][j].getDie().getValue() == 4) num[3]++;

                        if (board[i][j].getDie().getValue() == 5) num[4]++;

                        if (board[i][j].getDie().getValue() == 6) num[5]++;
                    }
        }

        for (int aNum : num) if (aNum < min) min = aNum;

        return min;
    }

    public String getE() {

        return "Different " + iscolorShades() + " everywhere";
    }

    @Override
    public void rewrite() {

        this.typeEffect = "DifferentColorShade";
    }
}