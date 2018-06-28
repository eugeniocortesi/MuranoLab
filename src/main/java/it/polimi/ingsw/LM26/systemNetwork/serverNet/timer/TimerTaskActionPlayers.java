package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerTaskActionPlayers class
 * TimerTask that manages the time of each move for each client
 */

public class TimerTaskActionPlayers extends TimerTask {

    private ServerBase serverBase;
    private TimerConfiguration timerConfiguration;
    private Timer timer;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());
    private boolean arrivedMessage;

    /**
     * Constructor
     * @param serverBase Server
     * @param timerConfiguration file of configuration of timer
     * @param timer timer
     * @param name name of player
     */
    public TimerTaskActionPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration, Timer timer, String name) {

        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        this.serverBase = serverBase;
        this.timerConfiguration = timerConfiguration;
        this.timer = timer;
        this.name = name;
        this.arrivedMessage = false;
    }

    public boolean isArrivedMessage() {
        return arrivedMessage;
    }

    public void setArrivedMessage(boolean b){
        arrivedMessage = b;
    }

    /**
     * called by start
     */

    @Override
    public void run() {
        body();
    }

    /**
     * Every time that finish the timer checks if the player has sent an action
     * If the player has sent one the timer has finished his job
     * Otherwise it sends an ActionEventTimerEnd to the Controller
     */

    public void body(){
        if(//end time for the action, so didn't arrive action event
              arrivedMessage  ) {
            LOGGER.log(Level.SEVERE,"Reset timer action player");
            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
            timer.purge();

            //TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd(name, true);
            LOGGER.log(Level.SEVERE,"Timer end for "+ name);


            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
            timer.purge();
            serverBase.getQueueController().pushMessage(timerEnd);

        }
    }
}
