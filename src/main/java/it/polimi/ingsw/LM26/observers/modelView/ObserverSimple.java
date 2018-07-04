package it.polimi.ingsw.LM26.observers.modelView;

import it.polimi.ingsw.LM26.model.Model;

/**
 * ObserverSimple interface
 * @author Chiara Criscuolo
 * @param <T> generic type
 */
public interface ObserverSimple<T> {

    void update(Model m);
}
