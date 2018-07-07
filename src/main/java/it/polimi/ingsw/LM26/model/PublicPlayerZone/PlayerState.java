package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import java.io.Serializable;

/**
 * PlayerState enumerative class
 * @author Eugenio Cortesi
 */

public enum PlayerState implements Serializable {

    STANDBY, //client is not playing

    BEGINNING, //client is in his turn

    ENDING //it's not the client turn
}