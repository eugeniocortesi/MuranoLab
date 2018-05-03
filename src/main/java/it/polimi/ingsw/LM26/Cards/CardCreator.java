package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Effect;
import it.polimi.ingsw.LM26.Serialization.Matrix;
import it.polimi.ingsw.LM26.Serialization.Elements.elements;


public class CardCreator {

    public static CardInt createCard(elements colour) {
        CardInt card = ConcreteObjectivePrivateCardCreator.createCard(colour);
        return card;
    }

    public static CardInt createCard(int points, Effect effect) {
        CardInt card = ConcreteObjectivePublicCardCreator.createCard(points, effect);
        return card;
    }

    public static CardInt createCard(Effect effect) {
        CardInt card = ConcreteObjectivePublicCardCreator.createCard(effect);
        return card;
    }

    public static CardInt createCard(int num) {
        CardInt card = ConcreteToolCardCreator.createCard(num);
        return card;
    }

    public static CardInt createCard(int token, String title, Matrix matrix) {
        CardInt card = ConcreteWindowPatternCardCreator.createCard(token, title, matrix);
        return card;
    }

    public static CardInt createCard(Color color) {
        CardInt card = ConcreteWindowFramePlayerBoardCreator.createCard(color);
        return card;
    }


}
