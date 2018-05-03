package it.polimi.ingsw.LM26.Cards;

public class WindowFramePlayerBoard implements CardInt {

    public Box BoardMatrix[][];
    public Color color;

    public WindowFramePlayerBoard(Color color) {

        BoardMatrix = new Box[4][5];
        this.color=color;
    }
}
