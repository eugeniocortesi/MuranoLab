package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

public class WindowFramePlayerBoard/* implements CardInt */{

    private Box BoardMatrix[][];
    private Color color;
    private int id;
    private boolean hasPatternCard = false;
    private boolean empty= true;


    public WindowFramePlayerBoard(int id, Color color) {

        BoardMatrix = new Box[4][5];
        for(int i=0; i<4; i++)
            for(int j=0; j<5; j++)
                BoardMatrix[i][j]= new Box(i, j);
        this.color=color;
        this.id=id;
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

    public void insertPatternIntoBoard(PatternBox[][] patternMatrix){

        hasPatternCard=true;

        for(int i=0; i<4; i++)
            for(int j=0; j<5; j++)
                BoardMatrix[i][j].setPatternBox(patternMatrix[i][j]);
        }

    public boolean isHasPatternCard() {
        return hasPatternCard;
    }

    public void setHasPatternCard(boolean hasPatternCard) {
        this.hasPatternCard = hasPatternCard;
    }

    public Box[][] getBoardMatrix() {
        return BoardMatrix;
    }

    public void printCard(){

        System.out.println("printing card " + getId() + " " + getColor());

        if (hasPatternCard)
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                if(BoardMatrix[i][j].isIsPresent())
                    System.out.print("\t"+BoardMatrix[i][j].getDie().toString() ) ;
                else {
                    if (BoardMatrix[i][j].getPatternBox().isColor() == true){
                        String escape =BoardMatrix[i][j].getPatternBox().getColor().escape();
                        System.out.print("\t"+escape +"\u25A0" + Color.RESET + " " );}
                    if (BoardMatrix[i][j].getPatternBox().isShade() == true)
                        System.out.print("\t"+BoardMatrix[i][j].getPatternBox().getValue() + " ");
                }
            }

            System.out.println();
        }


    }
}
