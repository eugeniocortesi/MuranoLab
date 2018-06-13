package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.controllerHandler.EventHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerState;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class Controller implements ControllerInt{

    private Model model;

    private Match match;

    private ViewGameInterface server;

    private UpdatesHandler updatesHandler;

    private SetupHandler setupHandler;

    private Boolean gameIsGoing;

    public Controller() {

        this.model = singletonModel();

        gameIsGoing = false;

    }

    public void startServer(){

        updatesHandler = new UpdatesHandler(this);

        setupHandler = new SetupHandler(this, model);

        server= new ServerBase(updatesHandler);

        server.startAcceptor(updatesHandler, model);
    }

    public SetupHandler getSetupHandler() {
        return setupHandler;
    }

    public ViewGameInterface getViewGameInterface(){
        return server;
    }

    @Override
    public boolean handler(ActionEvent event) {

        EventHandler handler = new EventHandler(event, model );

        return handler.getResult();
    }

    @Override
    public void playersReady() {
        gameIsGoing = true;

        for(int i = 0; i< model.getPlayerList().size(); i++){

            if(model.getPlayerList().get(i).getWindowPatternCard()== null && model.getPlayerList().get(i)!= null){

                System.out.println("window pattern is null from " +model.getPlayerList().get(i).getName());
                gameIsGoing = false;
            }

        }

        if(gameIsGoing== true){

            model.hasChanged();
            System.out.println("playerlist "+ model.getPlayerList());
            for(int i = 0; i<model.getPlayerList().size(); i++){
                System.out.println(model.getPlayerList().get(i)+ "player");
            }

            newMatch(model, this);

        }
    }

    public void newMatch(Model model, ControllerInt controller/*, ArrayList<ConsoleStrings> console*/){

        this.match=new Match(model, controller);

    }

    public void setupPrivateCard() {

        int count = model.getDecks().getObjectivePrivateCardDeck().size();

        for (int j = 0; j < model.getPlayerList().size(); j++) {

            Random rand = new Random();
            int index = rand.nextInt(count);
            while (model.getDecks().getObjectivePrivateCardDeck().get(index).isInUse() == true) {
                rand = new Random();
                index = rand.nextInt(count);
            }
            model.getDecks().getObjectivePrivateCardDeck().get(index).setInUse(true);
                server.showPrivateCard(model.getPlayerList().get(j).getName(), model.getDecks().getObjectivePrivateCardDeck().get(index));
            }

    }

    public void setupWindowCard(){

        for(int i=0; i< model.getPlayerList().size(); i++){
            ArrayList<WindowPatternCard> windowlist = setupHandler.createWindowPattern();
            System.out.println(windowlist.size());
            if(model.getPlayerList().get(i).getName() == null)
                    System.out.println("name null");
            else if(model.getDecks().getWindowPatternCardDeck()== null)
                System.out.println("cards null");
            if(server== null)
                System.out.println("server: " + server);
            server.showWindowPattern(model.getPlayerList().get(i).getName(), model.getPlayerList().get(i).getIDPlayer(), windowlist);
            windowlist.clear();

        }
    }


    //TODO remove later
    public void setActionEvent(ActionEvent newEvent ){

        match.setActionEvent(newEvent);
    }

    public void setStandbyPlayer(String namePlayer) {

        model.getPlayer(namePlayer).setPlayerState(PlayerState.STANDBY);
    }

}


