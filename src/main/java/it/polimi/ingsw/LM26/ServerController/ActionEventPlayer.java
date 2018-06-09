package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionEventPlayer extends ClassMessage implements Serializable {

    private String namePlayer;

    private boolean connection;


    public ActionEventPlayer(String m, boolean connection){
        this.namePlayer=m;
        this.connection=connection;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEventPlayer(this);
    }
}
