package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Matrix;


public class WindowPatternCard implements CardInt {

    private int token;
    private String title;
    private Matrix matrix;
    private PatternBox[][] patternMatrix;


    public WindowPatternCard(int token, String title, Matrix matrix) {

        this.token = token;
        this.title = title;
        this.matrix = matrix;
    }

    public void createPattern(){
        patternMatrix = new PatternBox[4][5];
        for(int i=0; i<4; i++)
            for(int j=0; j<5; j++)
                patternMatrix[i][j]= new PatternBox();

        matrix.createPatternMatrix(patternMatrix);
        this.matrix= null;
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

    public void printCard() {

        System.out.println(getTitle());
        System.out.println(getToken());

        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                if (patternMatrix[i][j].isColor() == true)
                    System.out.println(patternMatrix[i][j].getColor());
                if (patternMatrix[i][j].isShade() == true)
                    System.out.println(patternMatrix[i][j].getValue());
            }
            System.out.println();
        }
    }
}

