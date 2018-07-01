package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimerActionPlayer {

    private Timer timer;

    private long myLong;

    private static final Logger LOGGER = Logger.getLogger(TimerActionPlayer.class.getName());

    public TimerActionPlayer(long timerEnd){

        myLong = timerEnd;

        timer = new Timer();

    }

    public TimerTaskActionEvent scheduleTimerActionPlayer(SetupHandler setupHandler, String namePlayer){

        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER ACTION PLAYER");
        TimerTaskActionEvent timerTaskActionEvent = new TimerTaskActionEvent( timer , setupHandler, namePlayer);
        timer.schedule(timerTaskActionEvent , this.myLong, this.myLong);
        return  timerTaskActionEvent;
    }

    public void resetTimer(){
        timer = new Timer();
    }
}
