package it.polimi.ingsw.LM26.modelView;

import it.polimi.ingsw.LM26.model.Model;

public interface ObserverSimple<T> {

    void update(Model m);
}
