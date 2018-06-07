package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionEventPlayer extends ClassMessage implements Serializable {

    private String methodPlayer;

    private ArrayList<String> users;

    public ActionEventPlayer(String m, ArrayList<String> u){
        methodPlayer = m;
        users = u;
    }

    public String getMethodPlayer() {
        return methodPlayer;
    }

    public void setMethodPlayer(String methodPlayer) {
        this.methodPlayer = methodPlayer;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEventPlayer(this);
    }
}
