package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

import java.util.ArrayList;

/**
 * InitialPhase class
 * @author Eugenio Cortesi
 */

public class InitialPhase implements PhaseInt {

    private Model model = singletonModel();


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


    /**
     * it distributes tokens to player according to his window pattern card
     */

    public void setTokens(){

        for(PlayerZone i : model.getPlayerList()){

            Token token= new Token(i.getWindowPatternCard().getToken());

            i.setToken(token);
        }
    }


    /**
     * after a player got his window pattern card, this method calls the one that insert in into the players's board
     */

    private void insertPattern() {

        for(int i=0; i<model.getPlayerList().size(); i++) {

            PlayerZone player = model.getPlayer(i);

            player.getPlayerBoard().insertPatternIntoBoard(player.getWindowPatternCard().getWindowPatter());
        }
    }


    /**
     * this method is called to launch other methods of this class and create the next game phase
     * @param game instance of game, to set the new phase
     */

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
    public void endGame(PlayerZone last) {

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

    @Override
    public int getRoundNumber() {

        throw new UnsupportedOperationException("Not supported here");
    }
}
