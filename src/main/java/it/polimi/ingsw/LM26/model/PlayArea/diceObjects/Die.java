package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.util.Random;

public class Die implements DieInt {

    private Color color;

    private String face;

    public static final String[] faces = {
            "\u2680",
            "\u2681",
            "\u2682",
            "\u2683",
            "\u2684",
            "\u2685"
    };
    public void roll() {
        int count = faces.length;
        Random rand = new Random();
        int index = rand.nextInt(count);
        this.face = faces[index];
    }

    public int getValue() {

        int value=0;

        if(face=="\u2680")value = 1;
        if(face=="\u2681")value = 2;
        if(face=="\u2682")value = 3;
        if(face=="\u2683")value = 4;
        if(face=="\u2684")value = 5;
        if(face=="\u2685")value = 6;
        return value;
    }


    public void increment(){
        if(face=="\u2680")face="\u2681";
        if(face=="\u2681")face="\u2682";
        if(face=="\u2682")face="\u2683";
        if(face=="\u2683")face="\u2684";
        if(face=="\u2684")face="\u2685";
    }

    public void decrement(){
        if(face=="\u2681")face="\u2680";
        if(face=="\u2682")face="\u2681";
        if(face=="\u2683")face="\u2682";
        if(face=="\u2684")face="\u2683";
        if(face=="\u2685")face="\u2684";

    }

    public Die(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String escape = this.color.escape();
        return escape+"["+face+"]" + Color.RESET;
    }

    public void dump() {
        System.out.println(this);
    }
}
