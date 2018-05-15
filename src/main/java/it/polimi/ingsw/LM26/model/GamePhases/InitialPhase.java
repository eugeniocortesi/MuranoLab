package it.polimi.ingsw.LM26.model.GamePhases;

import it.polimi.ingsw.LM26.model.Cards.*;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.Token;

import java.util.ArrayList;

public class InitialPhase implements PhaseInt {

    private ArrayList<PlayerZone> playerList;
    private ObjectivePublicCard objectivePublicCard;

    public InitialPhase(ArrayList<PlayerZone> playerList, Decks decks) {
        this.playerList = playerList;

    }

    private final int cardsOnBoardsize=3; //se si ha tempo sarebbe meglio mettere questo limite nella "OnBoardCards"
    //e quello di 10 nella RoundTrack (che invece è nella classe Round)

    private int index;

    private int cardId; //sostituisce temporaneamente



    //distribuisce i token a tutti i giocatori in base alla loro windowPatternCard
    public void setTokens(ArrayList<PlayerZone> playerZones){
        for(PlayerZone i : playerZones){
            Token token= new Token(i.getWindowPatternCard().getToken());
            i.setToken(token);
        }

    }

    public void setPublicCards(OnBoardCards onBoardCards, Decks decks){
        ArrayList<ObjectivePublicCard> publicCardsOnBoard= new ArrayList<ObjectivePublicCard>();
        while(publicCardsOnBoard.size() <cardsOnBoardsize){
            index=(((int)Math.random())%decks.getObjectivePublicCardDeck().size())-1;
            objectivePublicCard = decks.getObjectivePublicCardDeck().get(index);
            if(objectivePublicCard.isInUse() == false){
                objectivePublicCard.setInUse(true);
                publicCardsOnBoard.add(objectivePublicCard);
            }
        }
        onBoardCards.setObjectivePublicCardList(publicCardsOnBoard);
        onBoardCards.setToolCardList(decks.getToolCardDeck());
    }
    //controller integra con distribuzione delle carte private nell'area dataprivate

    public void doAction(Game game, ArrayList<PlayerZone> playerList) {
        //assegnare token, dare carte..
        game.setPhase(new CentralPhase(playerList));
    }
}
