package it.polimi.ingsw.LM26.Cards;

public class ConcreteWindowPatternCardCreator extends CardCreatorAC{

    public static CardInt createCard(){
        return new WindowPatternCard();
    }
}
