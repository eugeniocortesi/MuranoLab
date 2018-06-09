package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

public class Receiver extends Thread{

    MessageQueue messageQueue;
    VisitorInt visitorInt;

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

        System.out.println("FOUND Message");
        message.accept(visitorInt);
        execute();
    }

    public void run(){
        System.out.println("RECEIVER STARTED");
        execute();
    }
}
