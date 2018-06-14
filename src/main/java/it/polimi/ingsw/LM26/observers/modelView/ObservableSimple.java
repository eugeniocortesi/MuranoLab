package it.polimi.ingsw.LM26.observers.modelView;

import it.polimi.ingsw.LM26.model.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableSimple<T> {

    private transient final List<ObserverSimple<T>> observers = new ArrayList<>();

    public void register(ObserverSimple<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void deregister(ObserverSimple<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    protected void notify(Model m) {
        synchronized (observers) {
            System.out.println("notifying " + observers.size() + " observers");
            for (ObserverSimple<T> observer : observers) {
                observer.update(m);
            }
        }
    }
}
