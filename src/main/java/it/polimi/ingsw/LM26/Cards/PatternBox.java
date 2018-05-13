package it.polimi.ingsw.LM26.Cards;

public class PatternBox {

    private Color color =null;
    private boolean isColor=false;
    private boolean isShade=false;
    private int value=0;

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
