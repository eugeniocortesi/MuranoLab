package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageQueue {

    private static final Logger LOGGER = Logger.getLogger(MessageQueue.class.getName());
    ConcurrentLinkedQueue<ClassMessage> queue;

    public MessageQueue(){
        queue = new ConcurrentLinkedQueue<ClassMessage>();
    }

    public void pushMessage(ClassMessage classMessage){

        queue.add(classMessage);
        LOGGER.log(Level.WARNING,"Added element");
        queue.forEach(message -> {LOGGER.log(Level.WARNING, "Element: " + message);});
    }

    public ClassMessage pullMessage(){

        /*LOGGER.log(Level.WARNING,"Added element");
        queue.forEach(message -> {LOGGER.log(Level.WARNING, "Element: " + message);});*/
        return queue.poll();
    }
}
