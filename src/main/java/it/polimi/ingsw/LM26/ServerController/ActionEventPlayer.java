package it.polimi.ingsw.LM26.ServerController;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionEventPlayer implements Serializable {

    private String methodPlayer;

    private ArrayList<String> users;

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
}
