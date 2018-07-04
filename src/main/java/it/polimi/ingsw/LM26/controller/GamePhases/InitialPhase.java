package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class InitialPhase implements PhaseInt {

    Model model = singletonModel();

    /**
     * this methods assigns to each player his coloured score marker and window frame board, according to his ID
     */

    public void setScoreMarkerAndWindowFrame(){

        ArrayList<PlayerZone> playerList = model.getPlayerList();

        Decks decks = model.getDecks();

        playerList.get(0).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(0));

        playerList.get(0).setScoreMarker(new ScoreMarker(Color.ANSI_RED,playerList.get(0)));

        playerList.get(1).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(1));

        playerList.get(1).setScoreMarker(new ScoreMarker(Color.ANSI_GREEN,playerList.get(1)));

        if(playerList.size()>2){

            playerList.get(2).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(2));

            playerList.get(2).setScoreMarker(new ScoreMarker(Color.ANSI_BLUE,playerList.get(2)));

            if(playerList.size()==4){

                playerList.get(3).setPlayerBoard(decks.getWindowFramePlayerBoardDeck().get(3));

                playerList.get(3).setScoreMarker(new ScoreMarker(Color.ANSI_PURPLE, playerList.get(3)));
            }
        }
    }

    //distribuisce i token a tutti i giocatori in base alla loro windowPatternCard

    public void setTokens(){

        for(PlayerZone i : model.getPlayerList()){

            Token token= new Token(i.getWindowPatternCard().getToken());

            i.setToken(token);

            System.out.println(i.getName()+" has "+ i.getToken().getTokenNumber()+" token");
        }
    }

    public void insertPattern() {

        for(int i=0; i<model.getPlayerList().size(); i++) {

            PlayerZone player = model.getPlayer(i);

            player.getPlayerBoard().insertPatternIntoBoard(player.getWindowPatternCard().getWindowPatter());
        }
    }


    //questo metodo va chiamato dopo aver assegnato la windowPatternCard

    @Override
    public void doAction(Game game) {

        setScoreMarkerAndWindowFrame();

        insertPattern();

        setTokens();

        game.setPhase(new CentralPhase());
    }

    @Override
    public void nextRound(Round round, Game game) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public Round getCurrentRound() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public int[] getTurn() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public PlayerZone getWinner() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public void endGame() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public int getNrounds() {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean getOnePlayer() {

        throw new UnsupportedOperationException("Not supported here");
    }
}
