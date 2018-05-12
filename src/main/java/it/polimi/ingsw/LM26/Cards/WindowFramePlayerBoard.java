package it.polimi.ingsw.LM26.Cards;

public class WindowFramePlayerBoard/* implements CardInt */{

    private Box BoardMatrix[][];
    private Color color;
    private int id;

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

    public void printCard(){

        System.out.println(getId());
        System.out.println(getColor());

    }
}
