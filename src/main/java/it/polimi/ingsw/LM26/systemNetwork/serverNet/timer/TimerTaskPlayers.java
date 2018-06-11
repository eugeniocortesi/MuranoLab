package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayers;

import java.util.TimerTask;

public class TimerTaskPlayers extends TimerTask {

    private ServerBase serverBase;
    private TimerConfiguration timerConfiguration;

    public TimerTaskPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration) {
        this.serverBase = serverBase;
        this.timerConfiguration = timerConfiguration;
    }

    @Override
    public void run() {
        body();
    }

    public void body(){
        if(serverBase.clientManagerListSize()<2) {
            TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
        } else{
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd("ready", true);
            System.out.println("Timer end");
            serverBase.getQueueController().pushMessage(timerEnd);
        }
    }
}
