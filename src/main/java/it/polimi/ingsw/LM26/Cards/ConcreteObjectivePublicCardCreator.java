package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Effect;

public class ConcreteObjectivePublicCardCreator extends CardCreator {

    public static CardInt createCard(int points, Effect effect){

        return new ObjectivePublicCard(points, effect);
    }

    public static CardInt createCard(Effect effect){

        return new ObjectivePublicCard(effect);
    }
}
