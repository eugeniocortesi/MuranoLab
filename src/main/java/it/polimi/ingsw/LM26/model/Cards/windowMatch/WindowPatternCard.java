package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.Cards.CardInt;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.Serialization.Matrix;


/**
 * WindowPatternCard class
 * @author Eugenio Cortesi
 */

public class WindowPatternCard extends CardInt {

    private int token=0;

    private String title;

    private Matrix matrix;

    private PatternBox[][] patternMatrix;

    private boolean inUse = false;

    private static final int row=4;

    private static final int column=5;

    public WindowPatternCard(){
    }

    public WindowPatternCard(int token, String title, Matrix matrix) {

        this.token = token;

        this.title = title;

        this.matrix = matrix;

        this.typeCard = "WindowPatternCard";
    }


    /**
     * the method creates the new PatternBox-structure and calls the method that transforms the elements-structure read from the file into the new one
     * to the current use in the game: the new structure is of type PatternBox
     */

    public void createPattern() {

        patternMatrix = new PatternBox[row][column];

        for (int i = 0; i < row; i++)

            for (int j = 0; j < column; j++)

                patternMatrix[i][j] = new PatternBox();

        matrix.createPatternMatrix(patternMatrix);

        this.matrix = null;
    }

    public boolean isInUse() {

        return inUse;
    }

    public void setInUse(boolean inUse) {

        this.inUse = inUse;
    }

    public String getTitle() {

        return title;
    }

    public int getToken() {

        return token;
    }

    public PatternBox[][] getWindowPatter() {

        return patternMatrix;
    }


    /**
     * method is useful to print the cards in tests, System.out can't be avoided
     */

    public void printCard() {

        System.out.println(getTitle());

        System.out.println(getToken());

        String escape;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (patternMatrix[i][j].isColor()) {

                    escape = patternMatrix[i][j].getColor().escape();

                    System.out.print(escape + "\u25A0" + Color.RESET + " ");
                } else if (patternMatrix[i][j].isShade())

                    System.out.print(patternMatrix[i][j].getValue() + " ");
            }

            System.out.println();
        }
    }

    @Override
    public void rewrite() {

        this.typeCard = "WindowPatternCard";
    }
}

