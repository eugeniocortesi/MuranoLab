package it.polimi.ingsw.LM26.model.PublicPlayerZone;

public enum PlayerState {

    STANDBY, //non sta giocando
    BEGINNING, // è il suo turno ma deve ancora fare la mossa
    ENDING // ha fatto la mossa, tocca al prossimo

}
