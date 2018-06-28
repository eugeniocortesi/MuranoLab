package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class OnBoardCards implements Serializable {

    private ArrayList<ObjectivePublicCard> objectivePublicCardList;

    private transient ArrayList<ToolCardInt> toolCardList;

    private ArrayList<Integer> toolNumberList;

    public OnBoardCards() {

        this.objectivePublicCardList = new ArrayList<ObjectivePublicCard>();

        this.toolCardList = new ArrayList<ToolCardInt>();

        this.toolNumberList = new ArrayList<Integer>();

        setPublicCards();

        setToolCard();
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

    public void setPublicCards() {

        Decks decks = singletonDecks();

        for (int i = 0; i < 3; i++) {

            int count = decks.getObjectivePublicCardDeck().size();

            Random rand = new Random();

            int index = rand.nextInt(count);

            if (index == count)

                index--;

            ObjectivePublicCard card = decks.getObjectivePublicCardDeck().get(index);

            if (!card.isInUse()) {

                card.setInUse(true);

                this.getObjectivePublicCardList().add(card);

            } else {

                i--;
            }
        }

        //TODO DELETE
        System.out.print("On boards Public cards: ");
        for (int j = 0; j < objectivePublicCardList.size(); j++)
            System.out.print(objectivePublicCardList.get(j).getId() + " ");
        System.out.print("\n");
    }

    public void setToolCard() {

        Decks decks = singletonDecks();

        int count = decks.getToolCardDeck().size();

        ArrayList<ToolCardInt> three = new ArrayList<ToolCardInt>();

        for (int j = 0; j < 3; j++) {

            Random rand = new Random();

            int index = rand.nextInt(count);

            if (index == count)

                index--;

            while (decks.getToolCardDeck().get(index).isInUse()) {

                rand = new Random();

                index = rand.nextInt(count);

                if (index == count)

                    index--;
            }

            decks.getToolCardDeck().get(index).setInUse(true);

            three.add(decks.getToolCardDeck().get(index));

            toolNumberList.add(index + 1);
        }

        toolCardList = three;

        //TODO DELETE
        System.out.print("On boards Tool cards: ");
        for (int j = 0; j < three.size(); j++)
            System.out.print(three.get(j).getNum() + " ");
        System.out.print("\n");
        System.out.println("Number of tool Card: ");
        toolNumberList.forEach(n -> System.out.println(n));
    }

    public ArrayList<WindowPatternCard> getFourWindowPattern() {

        Decks decks = singletonDecks();

        ArrayList<WindowPatternCard> temp = new ArrayList<WindowPatternCard>();

        ArrayList<WindowPatternCard> four = new ArrayList<WindowPatternCard>();

        temp.addAll(decks.getWindowPatternCardDeck());

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
}
