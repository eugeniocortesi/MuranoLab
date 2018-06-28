package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.controller.GamePhases.InitialPhase;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestInitialPhase {

    private Decks deck;
    private ArrayList<PlayerZone> playerList= new ArrayList<PlayerZone>();
    private OnBoardCards onBoardCards = new OnBoardCards();
    private InitialPhase initialPhase = new InitialPhase(playerList,deck, onBoardCards);

    @Before
    public void SetUpInitialPhase(){
        deck=singletonDecks();
        for(int i=0; i<4; i++){
            playerList.add(new PlayerZone("name", i));
        }
        for(int i=0; i<playerList.size();i++){
            playerList.get(i).setWindowPatternCard(deck.getWindowPatternCardDeck().get(i));
        }
    }



    @Test
    public void TestSetTokens(){
        initialPhase.setTokens(playerList);
        for(int i=0, j=0; i<playerList.size();i++, j++){
            int t=playerList.get(i).getToken().getTokenNumber();
            assertEquals(t, deck.getWindowPatternCardDeck().get(j).getToken());
        }
    }

    @Test
    //tests that every score marker has the same colour of its frame board
    public void TestSetScoreMarkerAndWindowFrame(){
        initialPhase.setScoreMarkerAndWindowFrame(playerList, deck);
        for(PlayerZone i : playerList){
            assertEquals(i.getPlayerBoard().getColor(), i.getScoreMarker().getColor());
            System.out.println(i.getIDPlayer());
        }
    }

}
