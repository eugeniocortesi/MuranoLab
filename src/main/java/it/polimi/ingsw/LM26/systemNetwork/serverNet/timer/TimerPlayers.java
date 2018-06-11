package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;

public class TimerPlayers {

    private long myLong;

    private TimerConfiguration timerConfiguration;

    private ServerBase serverBase;

    private Timer timer;


    public TimerPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration) {
        this.timerConfiguration = timerConfiguration;
        this.serverBase = serverBase;
        myLong = timerConfiguration.getTimerEnd();
        Timer timer = new Timer();
    }
    public void scheduleTimerPlayer(){

        timer.schedule(new TimerTaskPlayers(serverBase, timerConfiguration) , 0, this.myLong);

    }

}
