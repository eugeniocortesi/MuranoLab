package it.polimi.ingsw.LM26.network.server;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

// Contains hashMap to memorize each connection (value) with username as key
//TODO Observable?

public class ConnectionToController extends Observable {

    private HashMap<String, ClientHandlerInt> users;
    private ServerImpl myServer;

    public ConnectionToController(ServerImpl server){

        users = new HashMap<String, ClientHandlerInt>();
        myServer = server;
    }

    public boolean getConnections( String username){
        if (users.get(username) == null)
            return false;
        return true;
    }

    public int size(){
        return users.size();
    }

    public void add(String n, ClientHandlerInt clientHandlerInt){
        users.put(n, clientHandlerInt);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged();
    }

    // public ArrayList<PlayerZone> getPlayers(){}
    // public ActionEvent getActionEvent(){}
    // public WindowPatternCard getPatternCardChoosen(ArrayList<WindowPatternCard> four){}
}
