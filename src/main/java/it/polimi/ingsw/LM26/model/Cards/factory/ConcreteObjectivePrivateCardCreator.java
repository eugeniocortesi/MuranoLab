package it.polimi.ingsw.LM26.model.Cards.factory;

import it.polimi.ingsw.LM26.model.Cards.CardInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

public class ConcreteObjectivePrivateCardCreator extends CardCreator {

    public static CardInt createCard(int id, elements colour){

        return new ObjectivePrivateCard(id, colour);
    }
}

