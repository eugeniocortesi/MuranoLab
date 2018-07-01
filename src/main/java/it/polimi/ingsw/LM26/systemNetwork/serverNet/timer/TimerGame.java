package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class TimerGame
 * General timer that manages all timerTasks
 * @author Chiara Criscuolo
 */

public class TimerGame {

    private long myLong;

    private ServerBase serverBase;

    private Timer timerGame;

    private static final Logger LOGGER = Logger.getLogger(TimerGame.class.getName());

    /**
     * class constructor
     *
     * @param serverBase         Server
     * @param timerGameEnd milliseconds loaded by file of configuration of timer
     */
    public TimerGame(ServerBase serverBase, long timerGameEnd) {
        this.serverBase = serverBase;
        myLong = timerGameEnd;
        timerGame = new Timer();
    }

    /**
     * call method that schedule TimerTaskPlayers
     */
    public void scheduleTimerPlayer() {

        LOGGER.log(Level.SEVERE, "Started schedule");
        if (serverBase == null)
            LOGGER.log(Level.SEVERE, "Server is null");
        if (timerGame == null)
            LOGGER.log(Level.SEVERE, "Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMEPLAYER");
        timerGame.schedule(new TimerTaskPlayers(serverBase, timerGame), this.myLong, this.myLong);

    }
}