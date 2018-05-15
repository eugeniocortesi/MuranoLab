package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Matrix;

public class ConcreteWindowPatternCardCreator extends CardCreator {

        public static CardInt createCard(int token, String title, Matrix matrix){

            return new WindowPatternCard(token, title, matrix);
        }

}
