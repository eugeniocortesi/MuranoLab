package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Matrix;

import it.polimi.ingsw.LM26.Serialization.Elements.elements;

public class WindowPatternCard implements CardInt {

    private int token;
    private String title;
    private Matrix matrix;



    public WindowPatternCard(int token, String title, Matrix matrix) {

        this.token = token;
        this.title = title;
        this.matrix = matrix;

    }

    public String getTitle() {
        return title;
    }

    public int getToken() {
        return token;
    }

    public elements[][] getWindowPatter() {

        return matrix.getMatrix();
        }

    public void printWindowPatter() {

        matrix.printMatrix();

    }
}

