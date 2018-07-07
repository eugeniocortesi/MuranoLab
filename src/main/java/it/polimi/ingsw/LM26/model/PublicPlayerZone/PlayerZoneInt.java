package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import java.io.Serializable;

/**
 * PlayerZone abstract class
 * @author Eugenio Cortesi
 */

public abstract class PlayerZoneInt implements Serializable {

    String typePlayerZone;

    public abstract void rewrite();
}
