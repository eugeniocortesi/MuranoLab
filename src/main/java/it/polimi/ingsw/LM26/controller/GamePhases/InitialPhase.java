package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.ScoreMarker;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;
import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.util.ArrayList;
import java.util.Random;

public class InitialPhase implements PhaseInt {

    private ArrayList<PlayerZone> playerList;
    private ObjectivePublicCard objectivePublicCard;
    private Random random =new Random();
    private OnBoardCards onBoardCards;
    private Decks decks;
    private final int cardsOnBoardsize=3; //se si ha tempo sarebbe meglio mettere questo limite nella "OnBoardCards"
    //e quello di 10 nella RoundTrack (che invece è nella classe Round)
    private int index;

    public int getCardsOnBoardsize() {
        return cardsOnBoardsize;
    }

    public InitialPhase(ArrayList<PlayerZone> playerList, Decks decks, OnBoardCards onBoardCards) {

        this.playerList = playerList;
        this.decks = decks;
        this.onBoardCards = onBoardCards;
    }

    /**
     * this methods assigns to each player his coloured score marker and window frame board, according to his ID
     * @param playerList
     */

    public void setScoreMarkerAndWindowFrame(ArrayList<PlayerZone> playerList,  Decks decks ){

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
    public void setTokens(ArrayList<PlayerZone> playerZones){

        for(PlayerZone i : playerZones){
            Token token= new Token(i.getWindowPatternCard().getToken());
            i.setToken(token);
            System.out.println(i.getName()+" has "+ i.getToken().getTokenNumber()+" token");
        }

    }

    public void insertPattern(ArrayList<PlayerZone> playerList) {

        for(int i=0; i<playerList.size(); i++)
            playerList.get(i).getPlayerBoard().insertPatternIntoBoard(playerList.get(i).getWindowPatternCard().getWindowPatter());
    }


    //questo metodo va chiamato dopo aver assegnato la windowPatternCard
    public void doAction(Game game, ArrayList<PlayerZone> playerList) {

        setScoreMarkerAndWindowFrame(playerList, decks);
        insertPattern(playerList);
        setTokens(playerList);
        game.setPhase(new CentralPhase(playerList));

    }

    @Override
    public void nextRound(Round round, Game game) {

    }

    @Override
    public Round getCurrentRound() {
        return null;
    }

    @Override
    public int[] getTurn() {

        return null;
    }

    @Override
    public PlayerZone getWinner() {
        return null;
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
