package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

public class ConcreteObjectivePrivateCardCreator extends CardCreator {

    public static CardInt createCard(int id, elements colour){

        return new ObjectivePrivateCard(id, colour);
    }
}

