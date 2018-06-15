package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayers;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskPlayers extends TimerTask {

    private ServerBase serverBase;
    private TimerConfiguration timerConfiguration;
    private Timer timer;


    public TimerTaskPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration, Timer timer) {
        if(serverBase == null)
            System.out.println("Server is null");
        if(timerConfiguration == null)
            System.out.println("Timer configuration is null");
        this.serverBase = serverBase;
        this.timerConfiguration = timerConfiguration;
        this.timer = timer;
    }

    @Override
    public void run() {
        body();
    }

    public void body(){
        if(serverBase.clientManagerListSize()<2) {
            System.out.println("Reset timer");

            //TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd("ready", true);
            System.out.println("Timer end");

            //TODO this has to do the controller
            serverBase.setGameIsGoing(true);
            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
            timer.purge();
            serverBase.getQueueController().pushMessage(timerEnd);
            serverBase.getClientManagerList().checkNumberLogged();
        }
    }
}
