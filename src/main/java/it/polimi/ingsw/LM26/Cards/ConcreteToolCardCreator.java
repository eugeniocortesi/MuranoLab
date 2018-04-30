package it.polimi.ingsw.LM26.Cards;

public class ConcreteToolCardCreator extends CardCreatorAC {

    public static CardInt createCard(String title, String effect){

        return new ToolCard(title, effect);
    }
}
