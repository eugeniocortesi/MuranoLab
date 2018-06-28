package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.timer.TimerPlayers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerTaskPlayers class
 * When two players connect to the Server it wait *** seconds, then starts a new game
 */

public class TimerTaskPlayers extends TimerTask {

    private ServerBase serverBase;
    private TimerConfiguration timerConfiguration;
    private Timer timer;
    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());

    /**
     *
     * @param serverBase Server
     * @param timerConfiguration file of configuration  of timer
     * @param timer timer
     */
    public TimerTaskPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration, Timer timer) {
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        this.serverBase = serverBase;
        this.timerConfiguration = timerConfiguration;
        this.timer = timer;
    }

    /**
     * called by start
     */
    @Override
    public void run() {
        body();
    }

    /**
     * Every time that finish the timer checks there are at least 2 players
     * If there are sends an ActionEventTimerEnd to the Controller
     * Otherwise the timer resets
     */
    public void body(){
        if(serverBase.clientManagerListSize()<2) {
            LOGGER.log(Level.SEVERE,"Reset timer");

            //TimerPlayers timerPlayers = new TimerPlayers(serverBase, timerConfiguration);
            //timerPlayers.scheduleTimerPlayer();
        } else{
            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd("ready", true);
            LOGGER.log(Level.SEVERE,"Timer end");

            //TODO this has to do the controller
            serverBase.setGameIsGoing(true);
            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
            timer.purge();
            serverBase.getQueueController().pushMessage(timerEnd);
            serverBase.getClientManagerList().checkNumberLogged();
        }
    }
}
