package it.polimi.ingsw.LM26.model.Cards;

public class WindowFramePlayerBoard/* implements CardInt */{

    private Box BoardMatrix[][];
    private Color color;
    private int id;
    private boolean hasPatternCard = false;
    private boolean empty= false;


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

        System.out.println(getId());
        System.out.println(getColor());

        if (hasPatternCard)
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                if (BoardMatrix[i][j].getPatternBox().isColor() == true)
                    System.out.println(BoardMatrix[i][j].getPatternBox().getColor());
                if (BoardMatrix[i][j].getPatternBox().isShade() == true)
                    System.out.println(BoardMatrix[i][j].getPatternBox().getValue());
            }
            System.out.println();
        }


    }
}
