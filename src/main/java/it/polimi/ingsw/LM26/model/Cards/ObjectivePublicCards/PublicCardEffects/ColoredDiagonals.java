package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * PublicCard effect class
 * @author Eugenio Cortesi
 */

public class ColoredDiagonals extends Effect {

    private static final Logger LOGGER = Logger.getLogger(ColoredDiagonals.class.getName());

    public ColoredDiagonals() {

        this.typeEffect = "ColoredDiagonals";

        LOGGER.setLevel(Level.ALL);
    }

    public String getE() {

        return "Colored diagonals";
    }

    @Override
    public void rewrite() {

        this.typeEffect = "ColoredDiagonals";
    }


    /**
     * method reads matrix by diagonals
     * @param b board on which calculate points
     * @return points gained with this objective
     */

    @Override
    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();

        int count = 0;

        int i;

        int j;

        for (i = 0; i < 4; i++) {

            for (j = 0; j < 5; j++) {

                if (board[i][j].isIsPresent()) {

                    if ((i > 0 && j > 0 && board[i - 1][j - 1].isIsPresent()

                            && board[i][j].getDie().getColor().equals(board[i - 1][j - 1].getDie().getColor())) ||

                            (i < 3 && j < 4 && board[i + 1][j + 1].isIsPresent()

                                    && board[i][j].getDie().getColor().equals(board[i + 1][j + 1].getDie().getColor())) ||

                            (i > 0 && j < 4 && board[i - 1][j + 1].isIsPresent()

                                    && board[i][j].getDie().getColor().equals(board[i - 1][j + 1].getDie().getColor())) ||

                            (i < 3 && j > 0 && board[i + 1][j - 1].isIsPresent()

                                    && board[i][j].getDie().getColor().equals(board[i + 1][j - 1].getDie().getColor()))) {

                        count++;

                        LOGGER.log(Level.INFO, "adjacent dice " + board[i][j].getDie().getColor());
                    }
                }
            }
        }

        return count;
    }
}