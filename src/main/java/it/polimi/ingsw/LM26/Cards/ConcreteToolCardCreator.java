package it.polimi.ingsw.LM26.Cards;

public class ConcreteToolCardCreator extends CardCreator {

    public static CardInt createCard(int num){

        return new ToolCard(num);
    }
}
