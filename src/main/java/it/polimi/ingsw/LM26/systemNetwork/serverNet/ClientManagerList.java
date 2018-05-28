package it.polimi.ingsw.LM26.systemNetwork.serverNet;

import java.util.HashMap;

public class ClientManagerList {

    private HashMap<String, ClientManager> managerHashMap;

    public ClientManagerList(){
        managerHashMap = new HashMap<String, ClientManager>();
    }

    public boolean addClientManager(String name, ClientManager clientManager){
        if(managerHashMap.get(name) != null)
            return false;
        else{
            managerHashMap.put(name, clientManager);
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
}
