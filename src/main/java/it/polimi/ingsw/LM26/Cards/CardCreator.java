package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Effect;
import it.polimi.ingsw.LM26.Serialization.Matrix;
import it.polimi.ingsw.LM26.Serialization.Elements.elements;


public class CardCreator {

    public static CardInt createCard(int id, elements colour) {
        CardInt card = ConcreteObjectivePrivateCardCreator.createCard(id, colour);
        return card;
    }

    public static CardInt createCard(int id, int points, Effect effect) {
        CardInt card = ConcreteObjectivePublicCardCreator.createCard(id, points, effect);
        return card;
    }

    public static CardInt createCard(int id, Effect effect) {
        CardInt card = ConcreteObjectivePublicCardCreator.createCard(id, effect);
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

    public static CardInt createCard(int id, Color color) {
        CardInt card = ConcreteWindowFramePlayerBoardCreator.createCard(id, color);
        return card;
    }


}
