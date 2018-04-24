package it.polimi.ingsw.LM26.Cards;

public class CardCreatorAC {

    public CardInt createCard(String type) {
        CardInt card = null;
        if(type.equals("ObjectivePublicCard"))
            card = ConcreteObjectivePublicCardCreator.createCard();
        else if(type.equals("ObjectivePrivateCard"))
            card = ConcreteObjectivePrivateCardCreator.createCard();
        else if(type.equals("ToolCard"))
            card = ConcreteToolCardCreator.createCard();
        else if(type.equals("WindowPatterCard"))
            card = ConcreteWindowPatternCardCreator.createCard();
        return card;

    }
}
