package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimerPlayers {

    private long myLong;

    private TimerConfiguration timerConfiguration;

    private ServerBase serverBase;

    private Timer timer1;

    private Timer timer2;

    private Timer timer3;

    private static final Logger LOGGER = Logger.getLogger(TimerPlayers.class.getName());


    public TimerPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration) {
        this.timerConfiguration = timerConfiguration;
        this.serverBase = serverBase;
        myLong = timerConfiguration.getTimerEnd();
        timer1 = new Timer();
        timer2 = new Timer();
        timer3 = new Timer();
    }
    public void scheduleTimerPlayer(){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timer1 == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMEPLAYER");
        timer1.schedule(new TimerTaskPlayers(serverBase, timerConfiguration, timer1) , this.myLong);

    }

    public void scheduleTimerNetworkPlayer(String name){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timer2 == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER NETWORK PLAYER");
        timer2.schedule(new TimerTaskNetworkPlayers(serverBase, timerConfiguration, timer2, name) , this.myLong);

    }

    public TimerTaskActionPlayers scheduleTimerActionPlayer(String name){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timer3 == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER ACTION PLAYER");
        TimerTaskActionPlayers timerTaskActionPlayers = new TimerTaskActionPlayers(serverBase, timerConfiguration, timer3, name);
        timer3.schedule(timerTaskActionPlayers , this.myLong);
        return  timerTaskActionPlayers;
    }

    public void resetTimerActionPlayer(){
        timer3 = new Timer();
    }
}
