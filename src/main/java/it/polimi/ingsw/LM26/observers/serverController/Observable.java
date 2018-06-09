package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowInitialMessage;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    public void register(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void deregister(Observer<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    protected void notify(ActionEvent actionEvent){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateAction(actionEvent);
            }
        }
    }

    protected void notify(ActionEventPlayer actionPlayer){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updatePlayers(actionPlayer);
            }
        }
    }

    protected void notify(ActionEventWindow actionEventWindow){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateWindowPattern(actionEventWindow);
            }
        }
    }

    protected void notify(ConnectMessage connectMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void notify(DataMessage dataMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    protected void notify(WindowInitialMessage windowInitialMessage){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    protected void notify(WindowAnswerMessage windowAnswerMessage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notify(ActionEventTimerEnd actionEventTimerEnd) {
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateActionEventTimerEnd(actionEventTimerEnd);
            }
        }
    }

    public void notify(Boolean beginGame) {
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateBeginGame(beginGame);
            }
        }
    }
}
