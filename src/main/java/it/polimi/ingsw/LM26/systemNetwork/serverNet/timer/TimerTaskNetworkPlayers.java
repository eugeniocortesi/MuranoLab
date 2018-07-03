package it.polimi.ingsw.LM26.systemNetwork.serverNet.timer;

import it.polimi.ingsw.LM26.observers.serverController.ActionEventPlayer;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TimerTaskNetworkPlayers class
 * @author Chiara Criscuolo
 * Manages the timer of connection
 */

public class TimerTaskNetworkPlayers extends TimerTask {

    private ServerBase serverBase;

    private Timer timer;

    private ClientManager cm;

    private static final Logger LOGGER = Logger.getLogger(TimerTaskPlayers.class.getName());

    private volatile boolean connected;

    /**
     * Constructor
     * @param serverBase reference to the Server
     * @param timer timer
     * @param cm reference to the ClientManager
     */

    public TimerTaskNetworkPlayers(ServerBase serverBase, Timer timer, ClientManager cm) {

        this.serverBase = serverBase;

        this.timer = timer;

        this.cm = cm;

        this.connected = false;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Every time that finish the timer checks if the player is online
     * If the player is online the timer starts again
     * Otherwise it sends a disconnectMessage (actionEventPlayer) to the Controller
     */

    @Override
    public void run() {

        if(connected) {

            connected = false;

            cm.ping();

        } else{

            if(cm.getName()!= null){

                String name = cm.getName();

                ActionEventPlayer actionEventPlayer = new ActionEventPlayer(name, false);

                serverBase.getQueueController().pushMessage(actionEventPlayer);

                serverBase.stop(name);
            }
            else

                cm.stop();


            LOGGER.log(Level.SEVERE,"Timer end for "+ cm.getName());

            // Terminates this timer, discarding any currently scheduled tasks
            timer.cancel();

            timer.purge();

        }
    }




}
