package it.polimi.ingsw.LM26.Serialization;

import it.polimi.ingsw.LM26.Serialization.Elements.elements;

public class Effect3 extends Effect{

    private elements shades;

    public Effect3(elements shades) {

        this.shades = shades;
        this.type = "Effect3";
    }

    public String getE() { return "Shades " + shades; }

    protected void resolve(){}
}
