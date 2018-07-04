package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MessageQueue class
 * @author Chiara Criscuolo
 * It is a queue that can be access by more threads safely
 */

public class MessageQueue {

    private static final Logger LOGGER = Logger.getLogger(MessageQueue.class.getName());

    ConcurrentLinkedQueue<ClassMessage> queue;

    /**
     * Constructor
     */

    public MessageQueue(){
        queue = new ConcurrentLinkedQueue<ClassMessage>();

        LOGGER.setLevel(Level.OFF);
    }

    /**
     * It pushes a ClassMessage in the queue
     * @param classMessage generic type of message
     */

    public void pushMessage(ClassMessage classMessage){

        queue.add(classMessage);

        LOGGER.log(Level.WARNING,"Added element");

        queue.forEach(message -> {LOGGER.log(Level.WARNING, "Element: " + message);});
    }

    /*
    It pulls and delete a message form the queue
     */

    public ClassMessage pullMessage(){

        return queue.poll();
    }
}
