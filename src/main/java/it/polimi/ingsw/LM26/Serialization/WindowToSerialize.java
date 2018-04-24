package it.polimi.ingsw.LM26.Serialization;

import java.io.Serializable;

public class WindowToSerialize implements Serializable {
    private String colour;
    private int number;

    public WindowToSerialize(String colour, int number){
        this.colour=colour;
        this.number=number;
    }

}