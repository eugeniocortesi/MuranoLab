package it.polimi.ingsw.LM26.Cards;

import it.polimi.ingsw.LM26.Serialization.Matrix;

public class ConcreteWindowPatternCardCreator {

        public static CardInt createCard(int token, String title, Matrix matrix){

            return new WindowPatternCard(token, title, matrix);
        }

}
