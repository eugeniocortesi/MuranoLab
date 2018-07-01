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
    private Timer timer;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());
    private volatile boolean arrivedMessage;

    /**
     * Constructor
     * @param serverBase Server
     * @param timer timer
     * @param name name of player
     */
    public TimerTaskActionPlayers(ServerBase serverBase, Timer timer, String name) {

        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        this.serverBase = serverBase;
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
            //TimerGame timerPlayers = new TimerGame(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd(name, true);
            LOGGER.log(Level.SEVERE,"Timer end for "+ name);

            serverBase.getQueueController().pushMessage(timerEnd);



        }


        timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
        timer.purge();

    }
}
