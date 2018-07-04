package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Receiver class
 * @author Chiara Criscuolo
 * It is a thread that has the job to pull messaged from messagedQueue and to call visitor on it
 */

public class Receiver extends Thread{

    MessageQueue messageQueue;

    VisitorInt visitorInt;

    private static final Logger LOGGER = Logger.getLogger(Receiver.class.getName());

    /**
     * Constructor
     * @param messageQueue queue of messages
     * @param visitorInt instance of Visitor
     */
    public Receiver(MessageQueue messageQueue, VisitorInt visitorInt){

        this.messageQueue = messageQueue;

        this.visitorInt = visitorInt;

        LOGGER.setLevel(Level.OFF);
    }

    public VisitorInt getVisitorInt() {
        return visitorInt;
    }

    private void execute(){
        ClassMessage message = null;

        while(message == null){

            message = messageQueue.pullMessage();
        }

        LOGGER.log(Level.WARNING,"FOUND Message");

        message.accept(visitorInt);

        execute();
    }

    public void run(){

        execute();
    }
}
