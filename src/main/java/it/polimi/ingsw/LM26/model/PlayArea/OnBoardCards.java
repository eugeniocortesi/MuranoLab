package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

/**
 * OnBoardCard class
 * @author Eugenio Cortesi
 * class sores the selected cards that remains on the 'table' in an area shared by all players
 */

public class OnBoardCards implements Serializable {

    private ArrayList<ObjectivePublicCard> objectivePublicCardList;

    private transient ArrayList<ToolCardInt> toolCardList;

    private ArrayList<Integer> toolNumberList;

    private int[] cardsToken;

    public OnBoardCards() {
    }

    /**
     * Constructor
     * it creates the structures and calls the method two distribute the card needed randomly
     * @param s parameter to distinguish this from the empty constructor
     */

    public OnBoardCards(String s) {

        this.objectivePublicCardList = new ArrayList<>();

        this.toolCardList = new ArrayList<>();

        this.toolNumberList = new ArrayList<>();

        cardsToken = new int[3];

        setPublicCards();

        setToolCard();
    }


    /**
     * method distribute three random PublicCard.
     * it necessary a control that makes sure to not extract two times the same card
     */

    public void setPublicCards() {

        Model model = singletonModel();

        for (int i = 0; i < 3; i++) {

            int count = model.getDecks().getObjectivePublicCardDeck().size();

            Random rand = new Random();

            int index = rand.nextInt(count);

            while (model.getDecks().getObjectivePublicCardDeck().get(index).isInUse()) {

                rand = new Random();

                index = rand.nextInt(count);
            }

            model.getDecks().getObjectivePublicCardDeck().get(index).setInUse(true);

            objectivePublicCardList.add(model.getDecks().getObjectivePublicCardDeck().get(index));
        }
    }


    /**
     * method distribute three random PublicCard.
     * it necessary a control that makes sure to not extract two times the same card
     */

    private void setToolCard() {

        Model model = singletonModel();

        int count = model.getDecks().getToolCardDeck().size();

        ArrayList<ToolCardInt> three = new ArrayList<>();

        for (int j = 0; j < 3; j++) {

            Random rand = new Random();

            int index = rand.nextInt(count);

            while (model.getDecks().getToolCardDeck().get(index).isInUse()) {

                rand = new Random();

                index = rand.nextInt(count);
            }

            model.getDecks().getToolCardDeck().get(index).setInUse(true);

            three.add(model.getDecks().getToolCardDeck().get(index));

            toolNumberList.add(index + 1);

            cardsToken[j] = 1;
        }

        toolCardList = three;
    }


    /**
     * the method distribute four window pattern cards to all players (method recalled for each player), so they can choose the favorite.
     * the method is located here because the distribution action is shared with all the players,
     * but the only card stored is the one chosen by the client after the server sent him the four selected.
     * it necessary a control that makes sure to not extract two times the same card
     * @return four selected WindowPatternCards
     */

    public ArrayList<WindowPatternCard> giveFourWindowPattern() {

        Model model = singletonModel();

        ArrayList<WindowPatternCard> four = new ArrayList<>();

        ArrayList<WindowPatternCard> temp = new ArrayList<>(model.getDecks().getWindowPatternCardDeck());

        int count = temp.size();

        for (int j = 0; j < 4; j++) {

            Random rand = new Random();

            int index = rand.nextInt(count);

            while (temp.get(index).isInUse()) {

                rand = new Random();

                index = rand.nextInt(count);
            }

            temp.get(index).setInUse(true);

            four.add(temp.get(index));

            temp.remove(index);

            count = temp.size();
        }

        return four;
    }


    /**
     * the method distribute one private card to all players (method recalled for each player).
     * the method is located here because the distribution action is shared with all the players, even if the card are covered.
     * the cards are not stored here, because it's a shared area, but each card has the reference to the owner.
     * it necessary a control that makes sure to not extract two times the same card
     * @return four selected WindowPatternCards
     */

    public ObjectivePrivateCard distributePrivateCard(PlayerZone player) {

        Model model = singletonModel();

        Random rand = new Random();

        int count = model.getDecks().getObjectivePrivateCardDeck().size();

        int index = rand.nextInt(count);

        while (model.getDecks().getObjectivePrivateCardDeck().get(index).isInUse()) {

            rand = new Random();

            index = rand.nextInt(count);
        }

        model.getDecks().getObjectivePrivateCardDeck().get(index).setInUse(true);

        model.getDecks().getObjectivePrivateCardDeck().get(index).setPlayer(player);

        return model.getDecks().getObjectivePrivateCardDeck().get(index);
    }


    /**
     * Tool cards are not sent to clients, so this method stores the information to communicate them about the cost for use a card
     * @param toolCard tool card, used for the second time, of which the cost of usage must mu updated
     */

    public void setSecondUsage(ToolCardInt toolCard) {

        for (int i = 0; i < toolCardList.size(); i++)

            if (toolCardList.get(i).equals(toolCard))

                cardsToken[i] = 2;
    }

    public ArrayList<ObjectivePublicCard> getObjectivePublicCardList() {

        return objectivePublicCardList;
    }

    public ArrayList<ToolCardInt> getToolCardList() {

        return toolCardList;
    }

    public ArrayList<Integer> getToolArrayList() {

        return toolNumberList;
    }

    public int[] getCardsToken() {

        return cardsToken;
    }
}