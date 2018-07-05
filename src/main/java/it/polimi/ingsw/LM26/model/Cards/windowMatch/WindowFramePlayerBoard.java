package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;

public class WindowFramePlayerBoard implements Serializable {

    private Box[][] boardMatrix;

    private Color color;

    private int id;

    private boolean hasPatternCard = false;

    private boolean empty = true;

    private int numDice = 0;

    public WindowFramePlayerBoard(){
    }

    public WindowFramePlayerBoard(int id, Color color) {

        boardMatrix = new Box[4][5];

        for (int i = 0; i < 4; i++)

            for (int j = 0; j < 5; j++)

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

    public void insertPatternIntoBoard(PatternBox[][] patternMatrix) {

        hasPatternCard = true;

        for (int i = 0; i < 4; i++)

            for (int j = 0; j < 5; j++)

                boardMatrix[i][j].setPatternBox(patternMatrix[i][j]);
    }

    public int getEmptySpaces() {

        int count = 0;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 5; j++) {

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

    public void printCard() {

        String escape = color.escape();

        System.out.println("Window Frame Player Board " + escape + "\u25A0" + Color.RESET);

        if (hasPatternCard)

            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 5; j++) {

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