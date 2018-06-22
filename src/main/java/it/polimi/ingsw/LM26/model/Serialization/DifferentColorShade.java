package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;

public class DifferentColorShade extends Effect {

    //Colore= false
    //Sfumature = true

    private boolean colorShades;

    public DifferentColorShade(boolean colorShades) {

        this.colorShades = colorShades;
        this.typeEffect = "DifferentColorShade";
    }

    private String iscolorShades() {
        if (!colorShades)
            return "Colors";
        else return "Shades";
    }

    public String getE() {
        return "Different " + iscolorShades() + " everywhere";
    }

    protected void resolve() {
    }

    @Override
    public void rewrite() {

        this.typeEffect = "DifferentColorShade";

    }

    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();
        int[] num = new int[6];
        int min;

        if (!colorShades) {
            num[5] = 18;
            min=18;
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 5; j++)
                    if (board[i][j].isIsPresent()) {
                        if (board[i][j].getDie().getColor().equals(Color.ANSI_GREEN)) num[0]++;
                        if (board[i][j].getDie().getColor().equals(Color.ANSI_RED)) num[1]++;
                        if (board[i][j].getDie().getColor().equals(Color.ANSI_PURPLE)) num[2]++;
                        if (board[i][j].getDie().getColor().equals(Color.ANSI_BLUE)) num[3]++;
                        if (board[i][j].getDie().getColor().equals(Color.ANSI_YELLOW)) num[4]++;
                    }
        }
        else{
            min=20;
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 5; j++)
                    if (board[i][j].isIsPresent()) {
                        if (board[i][j].getDie().getValue()==1) num[0]++;
                        if (board[i][j].getDie().getValue()==2) num[1]++;
                        if (board[i][j].getDie().getValue()==3) num[2]++;
                        if (board[i][j].getDie().getValue()==4) num[3]++;
                        if (board[i][j].getDie().getValue()==5) num[4]++;
                        if (board[i][j].getDie().getValue()==6) num[5]++;
                    }
        }

        for (int i = 0; i < num.length; i++)
            if (num[i] < min) min = num[i];


        return min;
    }

    @Override
    public int getPoints() {
        return 0;
    }

}