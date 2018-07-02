package it.polimi.ingsw.LM26.controller.Testing;

import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.UpdatesHandler;
import it.polimi.ingsw.LM26.controller.controllerHandler.SetupHandler;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.observers.serverController.ActionEvent;
import it.polimi.ingsw.LM26.controller.ControllerInt;
import it.polimi.ingsw.LM26.controller.controllerHandler.EventHandler;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ServerBase;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.ViewGameInterface;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ControllerTest implements ControllerInt {

    private Model model;
    private ActionEvent event;
    private MatchTest match;
    private PhaseInt gamePhase;

    public ControllerTest() {

        this.model = singletonModel();
        setupPlayers();
        newMatch(model, this);

    }


    public boolean handler(ActionEvent event){
        EventHandler handler = new EventHandler(event, model,this );
        return handler.getResult();
    }

    @Override
    public void playersReady() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ViewGameInterface getViewGameInterface() {
        return null;
    }

    public UpdatesHandler getUpdatesHandler() { throw new UnsupportedOperationException("Not supported yet."); }


    public void setupPlayers() {

        PlayerZone player1 = new PlayerZone("eugenio", 0);
        PlayerZone player2 = new PlayerZone("Chiara", 1);
        PlayerZone player3 = new PlayerZone( "Claudia", 2);
        PlayerZone player4 = new PlayerZone("Tommaso", 3);

        player1.setNumberPlayer(1);
        player2.setNumberPlayer(2);
        player3.setNumberPlayer(3);
        player4.setNumberPlayer(4);

        ArrayList<PlayerZone> playerList = new ArrayList<PlayerZone>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);

        model.getDecks().getObjectivePrivateCardDeck().get(0).setPlayer(player1);
        model.getDecks().getObjectivePrivateCardDeck().get(1).setPlayer(player2);
        model.getDecks().getObjectivePrivateCardDeck().get(2).setPlayer(player3);
        model.getDecks().getObjectivePrivateCardDeck().get(3).setPlayer(player4);

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
                temp.get(index).setInUse(true);
                four.add(temp.get(index));
                temp.remove(index);
                count = temp.size();
            }

            playerList.get(i).setWindowPatternCard(temp.get(i));
        }
    }

    public void newMatch(Model model, ControllerTest controller){

        this.match=new MatchTest(model,controller);

    }

    public void setActionEvent(ActionEvent newEvent ){

        this.event=newEvent;

    }

    public ActionEvent getActionEvent() {
        System.out.println("event got from controllerTest");
        return event;
    }

    @Override
    public void declareScoresAndWinner(PlayerZone winner) {

    }

    @Override
    public void setEndGame() {

        gamePhase.endGame();

    }

    @Override
    public void setGamePhase(PhaseInt phase) {

        this.gamePhase=phase;
    }

    @Override
    public SetupHandler getSetupHandler() {
        return null;
    }

    @Override
    public ServerBase getServer() {

        return null;
    }


}
