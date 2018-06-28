package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import java.io.Serializable;

public class Shades extends Effect implements Serializable{

    private elements shades;

    public Shades(elements shades) {

        this.shades = shades;
        this.typeEffect = "Shades";
    }

    public String getE() { return "Shades " + shades; }

    protected void resolve(){}

    @Override
    public void rewrite() {

        this.typeEffect = "Shades";

    }

    @Override
    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();
        int shade1=0, shade2=0, count1=0, count2=0;

        if(shades==elements.DARK){
            shade1=5;
            shade2=6;
        }
        if(shades==elements.MEDIUMS){
            shade1=4;
            shade2=3;
        }
        if(shades==elements.LIGHT){
            shade1=1;
            shade2=2;
        }

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++)
                if (board[i][j].isIsPresent()) {
                    if (board[i][j].getDie().getValue()==shade1) count1++;
                    if (board[i][j].getDie().getValue()==shade2) count2++;
                }
        System.out.println("shades sets:");
        System.out.println(count1);
        System.out.println(count2);

        if(count1<count2) return( count1);
        else return (count2);
    }

    @Override
    public int getPoints() {
        return 0;
    }
}
