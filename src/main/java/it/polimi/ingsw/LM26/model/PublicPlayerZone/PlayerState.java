package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import java.io.Serializable;

public enum PlayerState implements Serializable {

    STANDBY, //non sta giocando
    BEGINNING, // è il suo turno ma deve ancora fare la mossa
    ENDING // ha fatto la mossa, tocca al prossimo

}
