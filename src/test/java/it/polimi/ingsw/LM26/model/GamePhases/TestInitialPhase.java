package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZoneInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static org.junit.Assert.assertEquals;

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
    //testa che il numero di toolcard sia giusto, stampa gli indici delle public card e verifica che siano tutti diversi
    public void TestSetPublicCards(){
        initialPhase.setPublicCards(onBoardCards, deck);
        int a=onBoardCards.getToolCardList().size();
        assertEquals(a, deck.getToolCardDeck().size());
        for(ObjectivePublicCard i : onBoardCards.getObjectivePublicCardList()){
            System.out.println(i.getId());
        }
        for(int i=0; i<initialPhase.getCardsOnBoardsize(); i++){
            for(int j=0; j<initialPhase.getCardsOnBoardsize(); j++){
                if(i!=j) assertThat(onBoardCards.getObjectivePublicCardList().get(i).getId(),is(not(onBoardCards.getObjectivePublicCardList().get(j).getId())));
            }
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
