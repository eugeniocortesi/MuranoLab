package it.polimi.ingsw.LM26.controller.controllerHandler;

import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.Random;

public class SetupHandler {

    private ControllerInt controller;
    private Model model;

    public SetupHandler(ControllerInt controllerInt, Model model) {
        controller = controllerInt;
        this.model = model;
    }


    public void setupPlayers(String name){


        if(model.getPlayer(name)==null){
            PlayerZone player = new PlayerZone(name, model.getPlayerList().size());
            player.setNumberPlayer(model.getPlayerList().size());
            model.getPlayerList().add(player);
            System.out.println(player.getName() + player.getIDPlayer());
        }
        else{
            model.getPlayer(name).setPlayerState(PlayerState.ENDING);
        }

    }

    public ArrayList<WindowPatternCard> createWindowPattern(){
        ArrayList<WindowPatternCard> temp = new ArrayList<WindowPatternCard>();
        ArrayList<WindowPatternCard> four = new ArrayList<WindowPatternCard>();

        temp.addAll(model.getDecks().getWindowPatternCardDeck());

        int count= temp.size();

        for (int j = 0; j < 4; j++) {

            Random rand = new Random();
            int index = rand.nextInt(count);
            while (temp.get(index).isInUse() == true) {
                rand = new Random();
                index = rand.nextInt(count);
            }
            temp.get(index).setInUse(true);
            four.add(temp.get(index));
            temp.remove(index);
            count = temp.size();
        }
        return four;
    }

    public void assignWindowCard(String name, WindowPatternCard windowPatternCard){

        windowPatternCard.printCard();

        model.getPlayer(name).setWindowPatternCard(windowPatternCard);
        model.getPlayer(name).getWindowPatternCard().printCard();
        System.out.println("Assigned card to player "+name);
        controller.playersReady();
    }

}
