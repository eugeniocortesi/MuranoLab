package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.observers.serverController.ActionEventTimerEnd;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;
import java.util.Random;

public class SetupHandler {

    private ControllerInt controller;

    private Model model;

    private ViewGameInterface server;

    public SetupHandler(ControllerInt controllerInt, Model model) {

        controller = controllerInt;

        this.model = model;
    }

    public void setupPlayers(String name) {

        if (model.getPlayer(name) == null) {

            PlayerZone player = new PlayerZone(name, model.getPlayerList().size());

            player.setNumberPlayer(model.getPlayerList().size());

            model.getPlayerList().add(player);

            System.out.println(player.getName() + player.getIDPlayer());
        }

        else model.getPlayer(name).setPlayerState(PlayerState.ENDING);
    }

    public void assignWindowCard(String name, WindowPatternCard windowPatternCard) {

        windowPatternCard.printCard();

        model.getPlayer(name).setWindowPatternCard(windowPatternCard);

        model.getPlayer(name).getWindowPatternCard().printCard();

        System.out.println("Assigned card to player " + name);

        controller.playersReady();
    }

    public void setStandbyPlayer(String namePlayer) {

        model.getPlayer(namePlayer).setPlayerState(PlayerState.STANDBY);

        ArrayList<PlayerZone> plNotStandby = new ArrayList<PlayerZone>();

        //TODO CODE TO KEEP: JUST DELETE COMMENTING

        /*for (int i = 0; i < model.getPlayerList().size(); i++) {

            if (model.getPlayerList().get(i).getName().equals(namePlayer))

                server.showAnswerFromController(namePlayer, "Sei in pausa.");

            else

                server.showAnswerFromController(model.getPlayerList().get(i).getName(), "Il player " + namePlayer + " è il pausa.");
        }*/

        for(int i =0; i< model.getPlayerList().size();i++)

            if(!model.getPlayerList().get(i).getPlayerState().equals(PlayerState.STANDBY))

                plNotStandby.add(model.getPlayerList().get(i));

        if(plNotStandby.size()==1)controller.setEndGame();

    }

    public void setupPrivateCard() {

        /*int count = model.getDecks().getObjectivePrivateCardDeck().size();

        for (int j = 0; j < model.getPlayerList().size(); j++) {

            Random rand = new Random();

            int index = rand.nextInt(count);

            while (model.getDecks().getObjectivePrivateCardDeck().get(index).isInUse()) {

                rand = new Random();

                index = rand.nextInt(count);
            }

            model.getDecks().getObjectivePrivateCardDeck().get(index).setInUse(true);

            model.getDecks().getObjectivePrivateCardDeck().get(index).setPlayer(model.getPlayerList().get(j));

            server.showPrivateCard(model.getPlayerList().get(j).getName(), model.getDecks().getObjectivePrivateCardDeck().get(index));
        }*/

        for (int j = 0; j < model.getPlayerList().size(); j++)

            server.showPrivateCard(model.getPlayerList().get(j).getName(), model.getOnBoardCards().getPrivateCard(model.getPlayerList().get(j)));
    }

    public void setupWindowCard() {

        for (int i = 0; i < model.getPlayerList().size(); i++) {

            ArrayList<WindowPatternCard> windowlist = model.getOnBoardCards().getFourWindowPattern();

            //TODO DELETE

            System.out.println(windowlist.size());
            if (model.getPlayerList().get(i).getName() == null)
                System.out.println("name null");
            else if (model.getDecks().getWindowPatternCardDeck() == null)
                System.out.println("cards null");
            if (server == null)
                System.out.println("server: " + server);

            server.showWindowPattern(model.getPlayerList().get(i).getName(), model.getPlayerList().get(i).getIDPlayer(), windowlist);

            windowlist.clear();
        }
    }

    /*public void setUpJumpTurn(ActionEventTimerEnd timerEnd) {

        model.getPlayer(timerEnd.getName()).getActionHistory().setJump(true);
    }*/

    public void setUpJumpTurn(String name) {

        model.getPlayer(name).getActionHistory().setJump(true);
    }


    public void setServer(ViewGameInterface server) {

        this.server = server;
    }
}
