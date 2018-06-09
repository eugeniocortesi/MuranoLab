package it.polimi.ingsw.LM26.model.Serialization;


import it.polimi.ingsw.LM26.model.Cards.windowMatch.PatternBox;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import java.io.Serializable;

import static it.polimi.ingsw.LM26.model.Serialization.Elements.elements.*;


public class Matrix implements Serializable {

    private elements matrix[][]= new elements[4][5];

    public Matrix(elements matrix11, elements matrix12, elements matrix13, elements matrix14, elements matrix15,
                  elements matrix21, elements matrix22, elements matrix23, elements matrix24, elements matrix25,
                  elements matrix31, elements matrix32, elements matrix33, elements matrix34, elements matrix35,
                  elements matrix41, elements matrix42, elements matrix43, elements matrix44, elements matrix45) {


        this.matrix[0][0] = matrix11;
        this.matrix[0][1] = matrix12;
        this.matrix[0][2] = matrix13;
        this.matrix[0][3] = matrix14;
        this.matrix[0][4] = matrix15;

        this.matrix[1][0] = matrix21;
        this.matrix[1][1] = matrix22;
        this.matrix[1][2] = matrix23;
        this.matrix[1][3] = matrix24;
        this.matrix[1][4] = matrix25;

        this.matrix[2][0] = matrix31;
        this.matrix[2][1] = matrix32;
        this.matrix[2][2] = matrix33;
        this.matrix[2][3] = matrix34;
        this.matrix[2][4] = matrix35;

        this.matrix[3][0] = matrix41;
        this.matrix[3][1] = matrix42;
        this.matrix[3][2] = matrix43;
        this.matrix[3][3] = matrix44;
        this.matrix[3][4] = matrix45;
    }


    public elements[][] getMatrix() {

        return matrix;
    }

    public void createPatternMatrix(PatternBox[][] patternMatrix){



        for(int i=0;i<4;i++)
            for(int j=0;j<5;j++){
                if( matrix[i][j]==RED){
                    patternMatrix[i][j].setColor(Color.ANSI_RED);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==GREEN){
                    patternMatrix[i][j].setColor(Color.ANSI_GREEN);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==BLUE){
                    patternMatrix[i][j].setColor(Color.ANSI_BLUE);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==VIOLET){
                    patternMatrix[i][j].setColor(Color.ANSI_PURPLE);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==YELLOW){
                    patternMatrix[i][j].setColor(Color.ANSI_YELLOW);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==EMPTY){
                    patternMatrix[i][j].setColor(Color.WHITE);
                    patternMatrix[i][j].setColorBool(true);}
                if( matrix[i][j]==LIGHT1){
                    patternMatrix[i][j].setValue(1);
                    patternMatrix[i][j].setShadeBool(true);}
                if( matrix[i][j]==LIGHT2){
                    patternMatrix[i][j].setValue(2);
                    patternMatrix[i][j].setShadeBool(true);}
                if( matrix[i][j]==MEDIUMS3){
                    patternMatrix[i][j].setValue(3);
                    patternMatrix[i][j].setShadeBool(true);}
                if( matrix[i][j]==MEDIUMS4){
                    patternMatrix[i][j].setValue(4);
                    patternMatrix[i][j].setShadeBool(true);}
                if( matrix[i][j]==DARK5){
                    patternMatrix[i][j].setValue(5);
                    patternMatrix[i][j].setShadeBool(true);}
                if( matrix[i][j]==DARK6){
                    patternMatrix[i][j].setValue(6);
                    patternMatrix[i][j].setShadeBool(true);}

            }

    }

    /*public void printMatrix() {

        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++)
                System.out.println(matrix[i][j]);
            System.out.println();
        }
    }*/
}
