package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Effect;

public class ConcreteObjectivePublicCardCreator extends CardCreator {

    public static CardInt createCard(int id, int points, Effect effect){

        return new ObjectivePublicCard(id, points, effect);
    }

    public static CardInt createCard(int id, Effect effect){

        return new ObjectivePublicCard(id, effect);
    }
}
