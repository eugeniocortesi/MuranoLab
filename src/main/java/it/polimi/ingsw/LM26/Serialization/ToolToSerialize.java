package it.polimi.ingsw.LM26.Serialization;

import java.io.Serializable;

public class ToolToSerialize implements Serializable {
    private String colour;
    private int number;

    public ToolToSerialize(String colour, int number){
        this.colour=colour;
        this.number=number;
    }

}