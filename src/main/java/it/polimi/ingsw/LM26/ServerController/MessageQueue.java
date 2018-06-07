package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue {

    ConcurrentLinkedQueue<ClassMessage> queue;

    public MessageQueue(){
        queue = new ConcurrentLinkedQueue<ClassMessage>();
    }

    public void pushMessage(ClassMessage classMessage){

        queue.add(classMessage);
        System.out.println("Added element");
    }

    public ClassMessage pullMessage(){
        return queue.poll();
    }
}
