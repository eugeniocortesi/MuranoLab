package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import java.io.Serializable;

public class Shades extends Effect implements Serializable{

    private elements shades;

    public Shades(elements shades) {

        this.shades = shades;
        this.type = "Shades";
    }

    public String getE() { return "Shades " + shades; }

    protected void resolve(){}

    @Override
    public void rewrite() {

        this.type = "Shades";

    }
}
