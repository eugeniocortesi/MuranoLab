package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver extends Thread{

    MessageQueue messageQueue;
    VisitorInt visitorInt;
    private static final Logger LOGGER = Logger.getLogger(Receiver.class.getName());

    public Receiver(MessageQueue messageQueue, VisitorInt visitorInt){
        this.messageQueue = messageQueue;
        this.visitorInt = visitorInt;
    }

    public VisitorInt getVisitorInt() {
        return visitorInt;
    }

    public void execute(){
        ClassMessage message = null;
        while(message == null){
            message = messageQueue.pullMessage();
        }

        LOGGER.log(Level.WARNING,"FOUND Message");
        message.accept(visitorInt);
        execute();
    }

    public void run(){
        LOGGER.log(Level.WARNING,"RECEIVER STARTED");
        execute();
    }
}
