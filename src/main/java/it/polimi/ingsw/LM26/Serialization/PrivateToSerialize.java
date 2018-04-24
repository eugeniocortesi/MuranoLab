package it.polimi.ingsw.LM26.Serialization;

import java.io.Serializable;

public class PrivateToSerialize implements Serializable {
    private String colour;
    private int number;

    public PrivateToSerialize(String colour, int number){
        this.colour=colour;
        this.number=number;
    }

}