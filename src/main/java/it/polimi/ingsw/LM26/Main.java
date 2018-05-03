package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.PlayArea.OnBoardCards;
import static it.polimi.ingsw.LM26.Serialization.Global.decks;



public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        //System.out.println(decks.getObjectivePublicCardDeck().get(0).getPoints());

        OnBoardCards onBoardCards = new OnBoardCards(decks.getObjectivePublicCardDeck(), decks.getToolCardDeck());

        //System.out.println(onBoardCards.getObjectivePublicCardList().get(0).getPoints());

    }
}
