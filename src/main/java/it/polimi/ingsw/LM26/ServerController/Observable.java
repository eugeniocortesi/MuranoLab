package it.polimi.ingsw.LM26.ServerController;

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

    protected void notify(ActionEventWindow actionPlayerWindow){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.updateWindowPattern(actionPlayerWindow);
            }
        }
    }
}
