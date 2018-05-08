package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Elements.elements;

public class ConcreteObjectivePrivateCardCreator extends CardCreator {

    public static CardInt createCard(int id, elements colour){

        return new ObjectivePrivateCard(id, colour);
    }
}

