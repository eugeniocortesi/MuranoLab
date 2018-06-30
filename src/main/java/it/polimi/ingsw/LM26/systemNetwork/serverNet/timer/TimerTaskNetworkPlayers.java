package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerTaskNetworkPlayers class
 * Manages the timer of connection
 */

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

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * called by start
     */
    @Override
    public void run() {
        LOGGER.log(Level.WARNING, "START SCHEDULE TIMER");
        body();
    }

    /**
     * Every time that finish the timer checks if the player is online
     * If the player is online the timer starts again
     * Otherwise it sends a disconnectMessage (actionEventPlayer) to the Controller
     */
    
    public void body(){
        if(connected) {
            LOGGER.log(Level.SEVERE,"Reset timer");
            connected = false;

            serverBase.ping(name);
            //TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{


            ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, false);
            LOGGER.log(Level.SEVERE,"Timer end for "+ name);

            // Terminates this timer, discarding any currently scheduled tasks
            timer.cancel();
            timer.purge();
            serverBase.getQueueController().pushMessage(actionEventPlayer);

        }
    }
}
