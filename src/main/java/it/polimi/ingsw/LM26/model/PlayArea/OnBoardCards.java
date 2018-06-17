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

    private transient Decks decks;

    public OnBoardCards() {

        this.objectivePublicCardList =new ArrayList<ObjectivePublicCard>() ;

        this.toolCardList = new ArrayList<ToolCardInt>();

        this.decks = singletonDecks();

        setPublicCards();

        setToolCard();
    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {
        return objectivePublicCardList;
    }

    public ArrayList<ToolCardInt> getToolCardList() {
        return toolCardList;
    }

    public void setPublicCards(){

        for(int i=0; i<3; i++ ) {
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

    public void setToolCard(){

        int count = decks.getToolCardDeck().size();
        ArrayList<ToolCardInt> three= new ArrayList<ToolCardInt>();

        for (int j = 0; j <3; j++) {
            Random rand = new Random();
            int index = rand.nextInt(count);
            while (decks.getToolCardDeck().get(index).isInUse() == true) {
                rand = new Random();
                index = rand.nextInt(count);
            }
            decks.getToolCardDeck().get(index).setInUse(true);
            three.add(decks.getToolCardDeck().get(index));

        }
        toolCardList=three;

        //TODO DELETE
        System.out.print("On boards Tool cards: " );
        for(int j=0; j<three.size(); j++)
            System.out.print(three.get(j).getNum()+  " ");
        System.out.print("\n");
    }


}

