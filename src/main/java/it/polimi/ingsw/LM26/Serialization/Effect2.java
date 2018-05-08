package it.polimi.ingsw.LM26.Serialization;

public class Effect2 extends Effect {

    //Colore= true
    //Sfumature = false

    private boolean colorShades;

    public Effect2(boolean colorShades) {

        this.colorShades = colorShades;
        this.type = "Effect2";
    }

    private String iscolorShades() {
        if (colorShades==true)
            return "Colors";
        else return "Shades";
    }

    public String getE() {
        return "Different " + iscolorShades() + " everywhere";
    }
    protected void resolve(){}
}
