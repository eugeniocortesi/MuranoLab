package it.polimi.ingsw.LM26.GamePhases;

import it.polimi.ingsw.LM26.Cards.Decks;
import it.polimi.ingsw.LM26.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.Cards.ToolCard;
import it.polimi.ingsw.LM26.Cards.WindowPatternCard;
import it.polimi.ingsw.LM26.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.PublicPlayerZone.Token;

import java.util.ArrayList;

public class InitialPhase implements PhaseInt {

    private ArrayList<PlayerZone> playerList;

    private final int cardsOnBoardsize=3;

    private int index;

    private int cardId; //sostituisce temporaneamente

//la deserializzazione e set dei deck va fatta qui?

    public void setTokens(WindowPatternCard windowPatternCard, PlayerZone playerZone){ //da fare per ciascun giocatore
        Token token= new Token(windowPatternCard.getToken());
        playerZone.setToken(token);
    }

    /*public void setPublicCards(OnBoardCards onBoardCards, Decks decks){
        ArrayList<ObjectivePublicCard> publicCardsOnBoard= new ArrayList<ObjectivePublicCard>();
        ArrayList<ToolCard> ToolCardsOnBoard= new ArrayList<ToolCard>();
        int i=0;
        while(i<cardsOnBoardsize &&){
            index=(((int)Math.random())%decks.getObjectivePublicCardDeck().size())-1;
            decks.getObjectivePublicCardDeck().get(index)
            publicCardsOnBoard.add();
            i++;
        }

        do {
            for(int i=0; i<cardsOnBoardsize; i++){
                index=(((int)Math.random())%decks.getObjectivePublicCardDeck().size())-1;
                ToolCardsOnBoard.add(decks.getToolCardDeck().get(index));
            }
        } while();
    }*/

    //controller integra con distribuzione delle carte private nell'area dataprivate

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {
        //assegnare token, dare carte..
        game.setPhase(new CentralPhase(playerList));
    }
}
