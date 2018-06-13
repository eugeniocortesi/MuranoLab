package it.polimi.ingsw.LM26.systemNetwork.serverNet;


import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.PlayerConnectionMessage;

import java.util.HashMap;

public class ClientManagerList{

    private HashMap<String, ClientManager> managerHashMap;
    private ServerBase myserver;


    public ClientManagerList(ServerBase serverBase){

        myserver = serverBase;
        managerHashMap = new HashMap<String, ClientManager>();

    }

    public HashMap<String, ClientManager> getManagerHashMap() {
        return managerHashMap;
    }

    public boolean addClientManager(String name, ClientManager clientManager){
        if(managerHashMap.get(name) != null)
            return false;
        else{
            managerHashMap.put(name, clientManager);
            //clientManager.setObservable(this);
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
        if(size()>= 2 && myserver.isGameIsGoing()){

            System.out.println("4 players logged");
            //TODO beginning game -> send notify to to controller
            myserver.registerModel();
            System.out.println("REGISTERED OBSERVER");
            PlayerConnectionMessage playerConnectionMessage = new PlayerConnectionMessage("begin_game", true);
            myserver.getQueueController().pushMessage(playerConnectionMessage);

            /*ArrayList<String> users= Collections.list(Collections.enumeration(managerHashMap.keySet()));
            ActionEventPlayer players = new ActionEventPlayer("ready", users);
            System.out.println("REGISTERED OBSERVER");

            System.out.println("4 players logged");
            myserver.getQueueController().pushMessage(players);

            //notify(players);*/

        }
    }
}
