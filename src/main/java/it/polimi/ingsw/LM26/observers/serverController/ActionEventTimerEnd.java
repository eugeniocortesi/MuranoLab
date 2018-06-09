package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;

public class ActionEventTimerEnd extends ClassMessage implements Serializable {

    private String name;

    private Boolean timerEnd;

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

    @Override
    public void accept(VisitorInt visitorInt) {

        visitorInt.visitActionEventTimerEnd(this);

    }
}
