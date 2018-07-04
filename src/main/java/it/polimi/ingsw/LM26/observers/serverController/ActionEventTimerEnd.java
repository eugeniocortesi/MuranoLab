package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;

/**
 * ActionEventTimerEnds class
 * @author Chiara Criscuolo
 * Contains the name of the player and if it has finished is time or not
 */

public class ActionEventTimerEnd extends ClassMessage implements Serializable {

    private String name;

    private Boolean timerEnd;

    /**
     * Constrcutor
     * @param name username of the player
     * @param timerEnd boolean (true = timer end, false = timer not end)
     */

    public ActionEventTimerEnd(String name, Boolean timerEnd) {
        this.name = name;

        this.timerEnd = timerEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTimerEnd() {
        return timerEnd;
    }

    public void setTimerEnd(Boolean timerEnd) {
        this.timerEnd = timerEnd;
    }

    /**
     * Calls the visitor pattern on the message
     * @param visitorInt instance of visitor
     */

    @Override
    public void accept(VisitorInt visitorInt) {

        visitorInt.visitActionEventTimerEnd(this);

    }
}
