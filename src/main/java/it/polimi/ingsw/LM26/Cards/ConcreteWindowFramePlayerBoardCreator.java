package it.polimi.ingsw.LM26.Cards;

public class ConcreteWindowFramePlayerBoardCreator extends CardCreator {

    public static CardInt createCard(Color color){

        return new WindowFramePlayerBoard(color);
    }
}
