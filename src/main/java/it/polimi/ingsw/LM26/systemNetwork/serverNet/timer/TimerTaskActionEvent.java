package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTaskActionEvent Class
 * @author Chiara Criscuolo
 * It is the task when the timerActionPlayer terminates
 */

public class TimerTaskActionEvent extends TimerTask{

    private Timer timer;

    private volatile boolean isArrived;

    private SetupHandler setupHandler;

    private String namePlayer;

    /**
     * Constructor
     * @param timer timer
     * @param setupHandler reference to setupHandler
     * @param namePlayer username of the player
     */

    public TimerTaskActionEvent(Timer timer, SetupHandler setupHandler, String namePlayer){

        this.setupHandler=setupHandler;

        this.namePlayer=namePlayer;

        this.timer= timer;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    /**
     * Method that overrides run
     */

    @Override
    public void run(){

        if(!isArrived){

            setupHandler.setUpJumpTurn(namePlayer);
        }

        //cancel timer and his tasks
        timer.cancel();

        timer.purge();
    }


    /**
     * Method that delete timer and his timerTask
     */

    public void cancelTimerTask(){

        //cancel timer and his tasks
        timer.cancel();

        timer.purge();

    }
}
