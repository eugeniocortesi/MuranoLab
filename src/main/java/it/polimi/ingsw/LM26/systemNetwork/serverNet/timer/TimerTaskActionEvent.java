package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.controller.Controller;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class TimerTaskActionEvent extends TimerTask{

    private Timer timer;

    private volatile boolean isArrived;

    private SetupHandler setupHandler;

    private String namePlayer;

    private static final Logger LOGGER = Logger.getLogger(TimerTaskActionEvent.class.getName());

    public TimerTaskActionEvent(Timer timer, SetupHandler setupHandler, String namePlayer){

        this.setupHandler=setupHandler;
        this.namePlayer=namePlayer;
        this.timer= timer;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    @Override
    public void run(){

        if(!isArrived){

            setupHandler.setUpJumpTurn(namePlayer);
        }
        timer.cancel();
        timer.purge();
    }
}
