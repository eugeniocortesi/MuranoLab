package it.polimi.ingsw.LM26.model.Serialization;

import java.io.Serializable;

public abstract class Effect implements Serializable {

     protected String type = "type";

     public abstract String getE();

     protected abstract void resolve();

     public abstract void rewrite();

}
