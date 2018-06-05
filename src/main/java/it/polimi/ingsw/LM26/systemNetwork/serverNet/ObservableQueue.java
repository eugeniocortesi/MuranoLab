package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import it.polimi.ingsw.LM26.ServerController.ActionEvent;
import it.polimi.ingsw.LM26.ServerController.ActionEventPlayer;
import it.polimi.ingsw.LM26.ServerController.Observable;
import it.polimi.ingsw.LM26.ServerController.Observer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ObservableQueue extends Observable {

    private ConcurrentLinkedQueue queue;

    public ObservableQueue(){
        queue = new ConcurrentLinkedQueue();
    }

    @Override
    public void register(Observer observer) {
        super.register(observer);
    }

    @Override
    protected void notify(ActionEvent message) {
        super.notify(message);
    }

    @Override
    protected void notify(ActionEventPlayer message) {
        super.notify(message);
    }
}
