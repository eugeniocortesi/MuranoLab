package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class OnBoardCards implements Serializable {

    private ArrayList<ObjectivePublicCard>  objectivePublicCardList;

    private ArrayList<ToolCardInt>  toolCardList;

    public OnBoardCards() {

        this.objectivePublicCardList =new ArrayList<ObjectivePublicCard>() ;

        this.toolCardList = new ArrayList<ToolCardInt>();

        Decks decks = singletonDecks();

        setPublicCards(decks);
    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {
        return objectivePublicCardList;
    }

    public ArrayList<ToolCardInt> getToolCardList() {
        return toolCardList;
    }

    public void setObjectivePublicCardList(ArrayList<ObjectivePublicCard> objectivePublicCardList) {
        this.objectivePublicCardList = objectivePublicCardList;
    }

    public void setToolCardList(ArrayList<ToolCardInt> toolCardList) {
        this.toolCardList = toolCardList;
    }

    public void setPublicCards(Decks decks){

        for(int i=0; i<3; i++ ) {

            //TODO add inUse
            Random random = new Random();
            ObjectivePublicCard card = decks.getObjectivePublicCardDeck()
                    .get(random.nextInt(decks.getObjectivePublicCardDeck().size() - 1));
            if (!card.isInUse()) {
                card.setInUse(true);
                this.getObjectivePublicCardList().add(card);
            } else {
                i--;
            }
        }

    }
       /* ArrayList<ObjectivePublicCard> publicCardsOnBoard= new ArrayList<ObjectivePublicCard>();
        while(publicCardsOnBoard.size() <cardsOnBoardsize){
            index=random.nextInt(decks.getObjectivePublicCardDeck().size()-1);
            objectivePublicCard = decks.getObjectivePublicCardDeck().get(index);
            if(!objectivePublicCard.isInUse()){
                objectivePublicCard.setInUse(true);
                publicCardsOnBoard.add(objectivePublicCard);
            }
        }*/

}

