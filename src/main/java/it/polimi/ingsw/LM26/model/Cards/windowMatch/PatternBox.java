package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;


/**
 * PatternBox class
 * @author Eugenio Cortesi
 * class creates the object that represents one single cell of the WindowPatternCard
 * a cell of this type can have a color or a shade and it stores the information about the two possibilities
 */

public class PatternBox  implements Serializable {

    private Color color = null;

    private boolean isColor = false;

    private boolean isShade = false;

    private int value = 0;

    PatternBox(){
    }

    public Color getColor() {

        return color;
    }

    public void setColor(Color color) {

        this.color = color;
    }

    public int getValue() {

        return value;
    }

    public void setValue(int value) {

        this.value = value;
    }

    public boolean isColor() {

        return isColor;
    }

    public void setColorBool(boolean color) {

        isColor = color;
    }

    public boolean isShade() {

        return isShade;
    }

    public void setShadeBool(boolean shade) {

        isShade = shade;
    }
}
