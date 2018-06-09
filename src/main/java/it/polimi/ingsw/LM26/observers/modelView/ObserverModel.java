package it.polimi.ingsw.LM26.observers.modelView;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ClientManager;

public class ObserverModel implements ObserverSimple {

    ClientManager clientManager;

    public ObserverModel(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void update(Model m) {
        clientManager.sendModel(m);
    }
}
