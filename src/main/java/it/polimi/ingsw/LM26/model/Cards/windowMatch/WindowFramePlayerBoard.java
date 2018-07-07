package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

/**
 * WindowFramePlayerBoard class
 * @author Eugenio Cortesi
 */

public class WindowFramePlayerBoard implements Serializable {

    private Box[][] boardMatrix;

    private Color color;

    private int id;

    private boolean hasPatternCard = false;

    private boolean empty = true;

    private int numDice = 0;

    private static final int row = 4;

    private static final int column = 5;

    public WindowFramePlayerBoard(){
    }

    public WindowFramePlayerBoard(int id, Color color) {

        boardMatrix = new Box[row][column];

        for (int i = 0; i < row; i++)

            for (int j = 0; j < column; j++)

                boardMatrix[i][j] = new Box(i, j);

        this.color = color;

        this.id = id;
    }

    public boolean isEmpty() {

        return empty;
    }

    public void setEmpty(boolean empty) {

        this.empty = empty;
    }

    public int getId() {

        return id;
    }

    public Color getColor() {

        return color;
    }


    /**
     * the method scands the matrix and assigns to each cell the one with same coordinates from the WindowPatternCard
     * @param patternMatrix matrix structure of the WindowPatternCard
     */

    public void insertPatternIntoBoard(PatternBox[][] patternMatrix) {

        hasPatternCard = true;

        for (int i = 0; i < row; i++)

            for (int j = 0; j < column; j++)

                boardMatrix[i][j].setPatternBox(patternMatrix[i][j]);
    }


    /**
     * the method is able to say how many cells with no linked die are in a board.
     * the computation is used in the final phase of the game.
     * @return number of empty spaces
     */

    public int getEmptySpaces() {

        int count = 0;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (!boardMatrix[i][j].isIsPresent()) count++;
            }
        }

        return count;
    }

    public Box[][] getBoardMatrix() {

        return boardMatrix;
    }

    public int getNumDice() {

        return numDice;
    }

    public void setNumDice(int numDice) {

        this.numDice = numDice;
    }

    public void incrementNumDice() {

        numDice = numDice + 1;
    }


    /**
     * method is useful to print the cards in tests, System.out can't be avoided
     */

    public void printCard() {

        String escape = color.escape();

        System.out.println("Window Frame Player Board " + escape + "\u25A0" + Color.RESET);

        if (hasPatternCard)

            for (int i = 0; i < row; i++) {

                for (int j = 0; j < column; j++) {

                    if (boardMatrix[i][j].isIsPresent())

                        System.out.print("\t" + boardMatrix[i][j].getDie().toString());

                    else {

                        if (boardMatrix[i][j].getPatternBox().isColor()) {

                            escape = boardMatrix[i][j].getPatternBox().getColor().escape();

                            System.out.print("\t" + escape + "\u25A0" + Color.RESET + " ");
                        }

                        if (boardMatrix[i][j].getPatternBox().isShade())

                            System.out.print("\t" + boardMatrix[i][j].getPatternBox().getValue() + " ");
                    }
                }

                System.out.println();
            }
    }
}