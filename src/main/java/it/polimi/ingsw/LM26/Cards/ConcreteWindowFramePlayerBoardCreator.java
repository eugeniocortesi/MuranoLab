package it.polimi.ingsw.LM26.Cards;

public class ConcreteWindowFramePlayerBoardCreator extends CardCreator {

    public static CardInt createCard(int id, Color color){

        return new WindowFramePlayerBoard(id, color);
    }
}
