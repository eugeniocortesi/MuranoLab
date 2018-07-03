package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerActionPlayer class
 * @author Chiara Criscuolo
 * Class that manages the timer for each move of the player
 */

public class TimerActionPlayer {

    private Timer timer;

    private long myLong;

    private static final Logger LOGGER = Logger.getLogger(TimerActionPlayer.class.getName());

    /**
     * Constructor
     * @param timerEnd milliseconds when the timer ends
     */

    public TimerActionPlayer(long timerEnd){

        myLong = timerEnd;

        timer = new Timer();

        LOGGER.setLevel(Level.OFF);
    }

    /**
     * Method that schedules the timer to do when finishes the TimerTaskActionEvent
     * @param setupHandler reference to SetupHandler in Controller
     * @param namePlayer username of the player
     * @return TimerTaskActionEvent
     */

    public TimerTaskActionEvent scheduleTimerActionPlayer(SetupHandler setupHandler, String namePlayer){

        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER ACTION PLAYER");

        TimerTaskActionEvent timerTaskActionEvent = new TimerTaskActionEvent( timer , setupHandler, namePlayer);

        timer.schedule(timerTaskActionEvent , this.myLong, this.myLong);

        return  timerTaskActionEvent;
    }

    /**
     * Method that reset timer
     */

    public void resetTimer(){

        timer = new Timer();
    }
}
