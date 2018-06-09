package it.polimi.ingsw.LM26.controller.Testing;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.EventHandler;
import it.polimi.ingsw.LM26.controller.Match;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ControllerTest implements ControllerInt {

    private Model model;

    private Match match;

    public ControllerTest() {

        this.model = singletonModel();

        //view.addObserver(this);

        setupPlayers();

        //TODO
        /*for(int i=0; i<model.getPlayerList().size(); i++){
        c.showLoginScreen
        }*/

        newMatch(model, this);

    }

    //TODO DELETE

    public boolean checkEvent( ActionEvent event){
        return false;
    }

    public boolean handler(ActionEvent event){
        EventHandler handler = new EventHandler(event, model );
        return handler.getResult();
    }

    @Override
    public void playersReady() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void showLogin(){

        //view.showLogin()
    }


   /* public void setupPlayers(ArrayList<String> names){
     //TODO GET FROM CONTROLLER
    }*/


    //TODO DELETE PLAYERS

    public void setupPlayers() {

        PlayerZone player1 = new PlayerZone("eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        // PlayerZone player3 = new PlayerZone( "Claudia", 2);
        // PlayerZone player4 = new PlayerZone("Tommaso", 3);

        player1.setNumberPlayer(0);
        player2.setNumberPlayer(1);
        // player3.setNumberPlayer(2);
        // player4.setNumberPlayer(3);

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();

        playerList.add(player1);
        playerList.add(player2);
        //playerList.add(player3);
        //playerList.add(player4);

        model.setPlayerList(playerList);


        ArrayList<WindowPatternCard> temp = new ArrayList<WindowPatternCard>();
        ArrayList<WindowPatternCard> four = new ArrayList<WindowPatternCard>();

        temp.addAll(model.getDecks().getWindowPatternCardDeck());

        int count = temp.size();


        for (int i = 0; i < playerList.size(); i++) {

            for (int j = 0; j < 4; j++) {

                Random rand = new Random();
                int index = rand.nextInt(count);
                while (temp.get(index).isInUse() == true) {
                    rand = new Random();
                    index = rand.nextInt(count);
                }

                four.add(temp.get(index));
                temp.remove(index);
                count = temp.size();
            }

            playerList.get(i).setWindowPatternCard(temp.get(i));
        }
    }

    public void newMatch(Model model, ControllerTest controller){

        this.match=new Match(model,controller);

    }


    //TODO DELETE
    public void setActionEvent(ActionEvent newEvent ){
        match.setActionEvent(newEvent);
    }


    public void close(){

    }



}
