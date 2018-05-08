package it.polimi.ingsw.LM26.Cards;

public class WindowFramePlayerBoard implements CardInt {

    public Box BoardMatrix[][];
    public Color color;
    int id;

    public WindowFramePlayerBoard(int id, Color color) {

        BoardMatrix = new Box[4][5];
        this.color=color;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
