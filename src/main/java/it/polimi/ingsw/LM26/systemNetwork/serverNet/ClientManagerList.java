package it.polimi.ingsw.LM26.systemNetwork.serverNet;


import it.polimi.ingsw.LM26.ServerController.ActionEventPlayer;
import it.polimi.ingsw.LM26.ServerController.ActionEventWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ClientManagerList{

    private HashMap<String, ClientManager> managerHashMap;
    private ServerBase myserver;
    private ObservableQueue controllerObservables;


    public ClientManagerList(ServerBase serverBase){

        myserver = serverBase;
        managerHashMap = new HashMap<String, ClientManager>();
        controllerObservables = new ObservableQueue();
    }

    public ObservableQueue getControllerObservables() {
        return controllerObservables;
    }

    public boolean addClientManager(String name, ClientManager clientManager){
        if(managerHashMap.get(name) != null)
            return false;
        else{
            managerHashMap.put(name, clientManager);
            //checkNumberLogged();
            return true;
        }

    }

    public void removeClientManager(String name){
        if(managerHashMap.get(name)!= null)
            managerHashMap.remove(name);
        else
            return;
    }

    public ClientManager getClientManager(String name){
        return managerHashMap.get(name);
    }

    public int size(){
        return managerHashMap.size();
    }

    public void checkNumberLogged(){
        if(size()>= 4){

            ArrayList<String> users= Collections.list(Collections.enumeration(managerHashMap.keySet()));
            ActionEventPlayer players = new ActionEventPlayer("ready", users);
            System.out.println("4 players logged");
            controllerObservables.register(myserver.getController());
            controllerObservables.notify(players);

        }
    }

    public void sendWindow(ActionEventWindow actionEventWindow){

        controllerObservables.notify(actionEventWindow);
    }

}
