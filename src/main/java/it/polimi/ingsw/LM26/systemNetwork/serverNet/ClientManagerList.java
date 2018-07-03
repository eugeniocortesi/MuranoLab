package it.polimi.ingsw.LM26.systemNetwork.serverNet;


import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.PlayerConnectionMessage;

import java.util.HashMap;

/**
 * ClientManagerList class
 * @author Chiara Criscuolo
 * Manages with a HashMap all the logged connection
 */

public class ClientManagerList{

    private HashMap<String, ClientManager> managerHashMap;

    private ServerBase myserver;

    /**
     * Constructor
     * @param serverBase reference to the Server
     */

    public ClientManagerList(ServerBase serverBase){

        myserver = serverBase;

        managerHashMap = new HashMap<String, ClientManager>();

    }

    public HashMap<String, ClientManager> getManagerHashMap() {

        return managerHashMap;
    }

    /**
     * Method that adds the new client in case it is not there jet
     * @param name username of the connection
     * @param clientManager reference to the ClientManager
     * @return boolean (true = added, false = not added)
     */

    public boolean addClientManager(String name, ClientManager clientManager){
        if(managerHashMap.get(name) != null)

            return false;

        else{

            managerHashMap.put(name, clientManager);

            return true;
        }
    }

    /**
     * Method that removes the ClientManager name
     * @param name name of the connection
     */

    public void removeClientManager(String name){

        if(managerHashMap.get(name)!= null)

            managerHashMap.remove(name);
    }

    public ClientManager getClientManager(String name){

        return managerHashMap.get(name);
    }

    public int size(){

        return managerHashMap.size();
    }

    /**
     * Checks if there already 2 players and game is going
     * In case begin Game pushing a message to the Controller
     */
    public void checkNumberLogged(){

        if(size()>= 2 && myserver.isGameIsGoing()){

            myserver.registerModel();

            PlayerConnectionMessage playerConnectionMessage = new PlayerConnectionMessage("begin_game", true);

            myserver.getQueueController().pushMessage(playerConnectionMessage);

        }
    }
}
