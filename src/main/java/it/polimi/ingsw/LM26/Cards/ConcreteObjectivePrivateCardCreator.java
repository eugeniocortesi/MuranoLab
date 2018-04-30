package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Elements.elements;

public class ConcreteObjectivePrivateCardCreator extends CardCreatorAC {

    public static CardInt createCard(elements colour){

        return new ObjectivePrivateCard(colour);
    }
}

