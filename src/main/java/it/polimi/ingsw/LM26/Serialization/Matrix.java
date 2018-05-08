package it.polimi.ingsw.LM26.Serialization;


import it.polimi.ingsw.LM26.Serialization.Elements.elements;


public class Matrix {

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

    public void printMatrix() {

        int i=0, j=0;
        for(i=0;i<4;i++){
            for(j=0;j<5;j++)
                System.out.println(matrix[i][j]);
            System.out.println();
        }
    }
}
