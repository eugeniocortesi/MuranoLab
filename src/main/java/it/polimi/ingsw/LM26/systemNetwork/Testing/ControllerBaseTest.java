package it.polimi.ingsw.LM26.systemNetwork.Testing;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;

import java.util.Observable;
import java.util.Observer;

public class ControllerBaseTest implements Observer {
    private Model model;
    private ServerBase server;

    public ControllerBaseTest(Model model, ServerBase server) {
        this.model = model;
        this.server = server;

    }

    @Override
    public synchronized void update(Observable o, Object arg) {

        if(arg.equals("ready")){
            System.out.println("TUTTI UTENTI COLLEGATI");
        }
    }
}
