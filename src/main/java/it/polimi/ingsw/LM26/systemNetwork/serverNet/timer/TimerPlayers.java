package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class TimerPlayers
 * General timer that manages all timerTasks
 * @author Chiara Criscuolo
 */

public class TimerPlayers {

    private long myLong;

    private final long myLongNetwork = 10000;

    private TimerConfiguration timerConfiguration;

    private ServerBase serverBase;

    private Timer timerGame;

    private Timer timerNC1;

    private Timer timerAP1;

    private static final Logger LOGGER = Logger.getLogger(TimerPlayers.class.getName());

    /**
     * class constructor
     * @param serverBase Server
     * @param timerConfiguration file of configuration of timer
     */
    public TimerPlayers(ServerBase serverBase, TimerConfiguration timerConfiguration) {
        this.timerConfiguration = timerConfiguration;
        this.serverBase = serverBase;
        myLong = timerConfiguration.getTimerEnd();
        timerGame = new Timer();
        timerNC1 = new Timer();
        timerAP1 = new Timer();
    }

    /**
     * call method that schedule TimerTaskPlayers
     */
    public void scheduleTimerPlayer(){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timerGame == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMEPLAYER");
        timerGame.schedule(new TimerTaskPlayers(serverBase, timerConfiguration, timerGame) , this.myLong, this.myLong);

    }

    /**
     * call method that schedule TimerNetworkPlayer
     * @param cm ClientManager of player
     */

    public TimerTaskNetworkPlayers scheduleTimerNetworkPlayer(ClientManager cm){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timerNC1 == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER NETWORK PLAYER");
        TimerTaskNetworkPlayers timerTaskNetworkPlayers = new TimerTaskNetworkPlayers(serverBase, timerConfiguration, timerNC1, cm);
        timerNC1.scheduleAtFixedRate( timerTaskNetworkPlayers ,this.myLongNetwork, this.myLongNetwork);
        return timerTaskNetworkPlayers;
    }

    /**
     * call method that schedule TimerTaskActionPlayer
     * @param name name of player
     */

    public TimerTaskActionPlayers scheduleTimerActionPlayer(String name){

        LOGGER.log(Level.SEVERE,"Started schedule");
        if(serverBase == null)
            LOGGER.log(Level.SEVERE,"Server is null");
        if(timerConfiguration == null)
            LOGGER.log(Level.SEVERE,"Timer configuration is null");
        if(timerAP1 == null)
            LOGGER.log(Level.SEVERE,"Timer is null");
        LOGGER.log(Level.WARNING, "STARTED SCHEDULE TIMER ACTION PLAYER");
        TimerTaskActionPlayers timerTaskActionPlayers = new TimerTaskActionPlayers(serverBase, timerConfiguration, timerAP1, name);
        timerAP1.schedule(timerTaskActionPlayers , this.myLong, this.myLong);
        return  timerTaskActionPlayers;
    }

    /**
     * reset timer3
     */

    public void resetTimerActionPlayer(){
        timerAP1 = new Timer();
    }
}
