package it.polimi.ingsw.LM26.controller.GamePhases;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;

public class TestInitialPhase {

    private Model model;
    private Decks deck;
    private ArrayList<PlayerZone> playerList= new ArrayList<PlayerZone>();
    private OnBoardCards onBoardCards = new OnBoardCards("s");
    private InitialPhase initialPhase = new InitialPhase();

    @Before
    public void SetUpInitialPhase(){
        model= singletonModel();
        model.reset();
        deck=model.getDecks();
        for(int i=0; i<4; i++){
            playerList.add(new PlayerZone("name", i));
        }
        for(int i=0; i<playerList.size();i++){
            playerList.get(i).setWindowPatternCard(deck.getWindowPatternCardDeck().get(i));
        }
        model.setPlayerList(playerList);
    }



    @Test
    public void TestSetTokens(){
        initialPhase.setTokens();
        for(int i=0, j=0; i<playerList.size();i++, j++){
            int t=playerList.get(i).getToken().getTokenNumber();
            assertEquals(t, deck.getWindowPatternCardDeck().get(j).getToken());
        }
    }

    @Test
    //tests that every score marker has the same colour of its frame board
    public void TestSetScoreMarkerAndWindowFrame(){
        initialPhase.setScoreMarkerAndWindowFrame();
        for(PlayerZone i : playerList){
            assertEquals(i.getPlayerBoard().getColor(), i.getScoreMarker().getColor());
            System.out.println(i.getIDPlayer());
        }
    }

}
