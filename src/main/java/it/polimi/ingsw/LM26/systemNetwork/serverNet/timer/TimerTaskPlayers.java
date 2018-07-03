package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerTaskPlayers class
 * @author Chiara Criscuolo
 * When two players connect to the Server it wait *** seconds, then starts a new game
 */

public class TimerTaskPlayers extends TimerTask {

    private ServerBase serverBase;

    private Timer timer;

    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());

    /**
     * Constructor
     * @param serverBase Server
     * @param timer timer
     */

    public TimerTaskPlayers(ServerBase serverBase, Timer timer) {

        this.serverBase = serverBase;

        this.timer = timer;
    }

    /**
     * Called by start
     * Every time that finish the timer checks there are at least 2 players
     * If there are sends an ActionEventTimerEnd to the Controller
     * Otherwise the timer resets
     */

    @Override
    public void run() {

        if(serverBase.clientManagerListSize()<2) {

            LOGGER.log(Level.SEVERE,"Reset timer");

        }
        else{

            ActionEventTimerEnd timerEnd = new ActionEventTimerEnd("ready", true);

            LOGGER.log(Level.SEVERE,"Timer end");

            serverBase.setGameIsGoing(true);

            serverBase.getQueueController().pushMessage(timerEnd);

            serverBase.getClientManagerList().checkNumberLogged();

            // Terminates this timer, discarding any currently scheduled tasks.

            timer.cancel();

            timer.purge();
        }
    }
}
