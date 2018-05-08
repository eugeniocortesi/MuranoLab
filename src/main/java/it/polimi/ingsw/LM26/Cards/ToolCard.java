package it.polimi.ingsw.LM26.Cards;

public class ToolCard implements CardInt  {

    private String title= null;
    private int num;


    public ToolCard(int num) {

        this.num=num;

    }

    public int getNum() {
        return num;
    }

    public void printCard(){

        System.out.println(getNum());

    }

}
