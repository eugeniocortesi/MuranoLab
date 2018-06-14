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
        timer = new Timer();
    }
    public void scheduleTimerPlayer(){

        System.out.println("Started schedule");
        if(serverBase == null)
            System.out.println("Server is null");
        if(timerConfiguration == null)
            System.out.println("Timer configuration is null");
        if(timer == null)
            System.out.println("Timer is null");
        timer.schedule(new TimerTaskPlayers(serverBase, timerConfiguration, timer) , this.myLong);

    }



}
