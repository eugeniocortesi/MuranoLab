package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * SetupHandler class
 * @author Eugenio Cortesi
 * this class apport modifies in model after updates
 */

public class SetupHandler {

    private ControllerInt controller;

    private Model model;

    private ViewGameInterface server;

    private static final Logger LOGGER = Logger.getLogger(SetupHandler.class.getName());

    public SetupHandler(ControllerInt controllerInt, Model model) {

        LOGGER.setLevel(Level.ALL);

        controller = controllerInt;

        this.model = model;
    }


    /**
     * @param name method gets a name and if the name already exist it means that the player was in standby and now is back in the game,
     *              else if the name wasn't in the players list it creates the player
     */

    public void setupPlayers(String name) {

        if (model.getPlayer(name) == null) {

            PlayerZone player = new PlayerZone(name, model.getPlayerList().size());

            player.setNumberPlayer(model.getPlayerList().size()+1);

            model.getPlayerList().add(player);

            LOGGER.log(Level.INFO,"ADDED "+ player.getName() + player.getIDPlayer() + player.getNumber());
        }

        else model.getPlayer(name).setPlayerState(PlayerState.ENDING);
    }


    /**
     * every time that a pattern has been set to the player, it calls the method that check if all player are ready to start the game
     * @param name of the player that choose one of the four window patter cards extracted for him
     * @param windowPatternCard chosen, to be set in the player
     */

    public void assignWindowCard(String name, WindowPatternCard windowPatternCard) {

        model.getPlayer(name).setWindowPatternCard(windowPatternCard);

        controller.playersReady();
    }


    /**
     * after that server notifies of a disconnected player or that a client decided to stop playing, this method is called and it sets the standby status
     * to the client in standby, it sends a message to all clients that this one went in standby and then it counts how many players remain in the game,
     * because when only one player remains it calls the endGame-Controller-method
     * @param namePlayer name of the client in standby
     */

    public void setStandbyPlayer(String namePlayer) {

        model.getPlayer(namePlayer).setPlayerState(PlayerState.STANDBY);

        ArrayList<PlayerZone> plNotStandby = new ArrayList<>();

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            if (model.getPlayerList().get(i).getName().equals(namePlayer))

                server.showAnswerFromController(namePlayer, "Sei in pausa.");

            else

                server.showAnswerFromController(model.getPlayerList().get(i).getName(), "Il player " + namePlayer + " e' in pausa.");
        }

        for(int i =0; i< model.getPlayerList().size();i++)

            if(!model.getPlayerList().get(i).getPlayerState().equals(PlayerState.STANDBY))

                plNotStandby.add(model.getPlayerList().get(i));

        if(plNotStandby.size()==1)controller.setEndGame();

    }


    /**
     * this method sends the selected private card to the assigned client
     */

    public void setupPrivateCard() {

        for (int j = 0; j < model.getPlayerList().size(); j++)

            server.showPrivateCard(model.getPlayer(j).getName(), model.getOnBoardCards().distributePrivateCard(model.getPlayer(j)));
    }

    /**
     * this method sends four window pattern cards to all the player, they will then choose one of them
     */

    public void setupWindowCard() {

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            ArrayList<WindowPatternCard> windowList = model.getOnBoardCards().giveFourWindowPattern();

            if(server!=null)

            server.showWindowPattern(model.getPlayer(i).getName(), model.getPlayer(i).getIDPlayer(), windowList);

            windowList.clear();
        }
    }

    /**
     *
     * @param timerEnd when the updates handler receives this event means that a player disconnected before the start of the game
     *                 (he did non answer the pattern card), so it must be deleted and deregistered from the list of clients that observe model
     */

    public void deletePlayer(ActionEventTimerEnd timerEnd) {

        PlayerZone toDelete= model.getPlayer(timerEnd.getName());

        model.getPlayerList().remove(toDelete);

        model.deregister(controller.getServer().getClientManagerList().getClientManager(timerEnd.getName()));

        controller.playersReady();
    }

    /**
     * this method is used when a action timer ends: a player ended this time and he must jump the turn
     * @param name of client that jump
     */

    public void setUpJumpTurn(String name) {

        model.getPlayer(name).getActionHistory().setJump(true);
    }

    public void setServer(ViewGameInterface server) {

        this.server = server;
    }
}
