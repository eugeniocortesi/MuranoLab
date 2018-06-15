package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimerTaskNetworkPlayers extends TimerTask {

    private ServerBase serverBase;
    private TimerConfiguration timerConfiguration;
    private Timer timer;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());
    private boolean connected;

    public TimerTaskNetworkPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration, Timer timer, String name) {

        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        this.serverBase = serverBase;
        this.timerConfiguration = timerConfiguration;
        this.timer = timer;
        this.name = name;
        this.connected = false;
    }

    @Override
    public void run() {
        body();
    }

    public void body(){
        if(connected) {
            LOGGER.log(Level.SEVERE,"Reset timer");

            //TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{

            //TODO Add disconnect message?
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd(name, true);
            LOGGER.log(Level.SEVERE,"Timer end for "+ name);


            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
            timer.purge();
            serverBase.getQueueController().pushMessage(timerEnd);

        }
    }
}
