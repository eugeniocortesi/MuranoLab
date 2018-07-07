package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * PublicCard effect class
 * @author Eugenio Cortesi
 */

public class Shades extends Effect implements Serializable{

    private elements shade;

    private static final Logger LOGGER = Logger.getLogger(ColoredDiagonals.class.getName());

    public Shades(elements shades) {

        this.shade = shades;

        this.typeEffect = "Shades";

        LOGGER.setLevel(Level.ALL);
    }


    /**
     * method counts the sets of the same shade gradient
     * @param b  board on which calculate points
     * @return points gained with this objective
     */

    @Override
    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();

        final int row=4;

        final int column=5;

        int shade1=0;

        int shade2=0;

        int count1=0;

        int count2=0;

        if(shade==elements.DARK){

            shade1=5;

            shade2=6;
        }

        if(shade==elements.MEDIUMS){

            shade1=4;

            shade2=3;
        }

        if(shade==elements.LIGHT){

            shade1=1;

            shade2=2;
        }

        for (int i = 0; i < row; i++)

            for (int j = 0; j < column; j++)

                if (board[i][j].isIsPresent()) {

                    if (board[i][j].getDie().getValue()==shade1) count1++;

                    if (board[i][j].getDie().getValue()==shade2) count2++;
                }

        LOGGER.log(Level.INFO, "shades sets: " + count1 + count2);

        if(count1<count2) return( count1);

        else return (count2);
    }

    public String getE() {

        return "Shades " + shade;
    }

    @Override
    public void rewrite() {

        this.typeEffect = "Shades";

    }
}
