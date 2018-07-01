package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Timer Player
 * It contains two timers that manage:
 * one: the network connection
 * two: the time for each action
 */

public class TimerPlayer {

    private Timer tNetworkConnection;

    private Timer tActionPlayer;

    private final long myLongNetwork = 20000;

    private long myLong;

    private ClientManager cm;

    private ServerBase serverBase;

    private static final Logger LOGGER = Logger.getLogger(TimerPlayer.class.getName());

    /**
     * Constructor
     * @param cm ClientManager of player
     * @param serverBase server
     */

    public TimerPlayer(ClientManager cm, ServerBase serverBase) {
        this.cm = cm;
        this.serverBase= serverBase;
        tActionPlayer = new Timer();
        tNetworkConnection = new Timer();
        myLong = serverBase.getTimerConfiguration().getTimerEnd();
    }

    /**
     * Start a timerTaskNetworkPlayers after myLongNetwork and has period my LongNetwork
     * It check if the client is alive
     * @return TimerTaskNetworkPlayers timerTask that does the check
     */

    public TimerTaskNetworkPlayers scheduleTNetwork(){

        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER NETWORK PLAYER");
        TimerTaskNetworkPlayers timerTaskNetworkPlayers = new TimerTaskNetworkPlayers(serverBase, tNetworkConnection, cm);
        tNetworkConnection.scheduleAtFixedRate( timerTaskNetworkPlayers ,this.myLongNetwork, this.myLongNetwork);
        return timerTaskNetworkPlayers;
    }

    /**
     * Starts the schedule the TimerTaskActionPlayers after myLong with period myLong
     * @return timerTaskActionPlayer timerTask that does the check
     */

    public TimerTaskActionPlayers scheduleTActionPlayer(){

        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER ACTION PLAYER");
        TimerTaskActionPlayers timerTaskActionPlayers = new TimerTaskActionPlayers(serverBase, tActionPlayer, cm.getName());
        tActionPlayer.schedule(timerTaskActionPlayers , this.myLong, this.myLong);
        return  timerTaskActionPlayers;
    }

    /**
     * Method that reset TimerActionPlayer creating a new Timer
     */

    public void resetTActionPlayer(){
        tActionPlayer = new Timer();
    }
}
