package it.polimi.ingsw.LM26.model.Serialization;

public class DifferentColorShade extends Effect {

    //Colore= true
    //Sfumature = false

    private boolean colorShades;

    public DifferentColorShade(boolean colorShades) {

        this.colorShades = colorShades;
        this.type = "DifferentColorShade";
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