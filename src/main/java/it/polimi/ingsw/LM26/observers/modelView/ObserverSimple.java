package it.polimi.ingsw.LM26.observers.modelView;

import it.polimi.ingsw.LM26.model.Model;

public interface ObserverSimple<T> {

    void update(Model m);
}
