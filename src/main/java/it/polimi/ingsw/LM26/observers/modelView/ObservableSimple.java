package it.polimi.ingsw.LM26.observers.modelView;

import it.polimi.ingsw.LM26.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * ObservableSimple abstract class
 * @author Chiara Criscuolo
 * @param <T> generic type
 */

public abstract class ObservableSimple<T> {

    private transient final List<ObserverSimple<T>> observers = new ArrayList<>();

    /**
     * register Observer
     * @param observer to be added to list
     */

    public void register(ObserverSimple<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * deregister Observer
     * @param observer to be removes from list
     */

    public void deregister(ObserverSimple<T> observer){
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * Notify all Observer about the changes in m
     * @param m new Model
     */
    protected void notify(Model m) {
        synchronized (observers) {

            for (ObserverSimple<T> observer : observers) {
                observer.update(m);
            }
        }
    }
}
