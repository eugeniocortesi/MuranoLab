package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;

public class ColoredDiagonals extends Effect{

    private String effect4="Colored diagonals";

    public ColoredDiagonals() {
        this.typeEffect = "ColoredDiagonals";
    }

    public String getE(){
        return effect4;
    }

    protected void resolve(){}

    @Override
    public void rewrite() {

        this.typeEffect = "ColoredDiagonals";

    }

    @Override
    public int checkEffect(WindowFramePlayerBoard b) {


        Box[][] board = b.getBoardMatrix();
        int count=0;
        int i, j;

        for(i=0;i<4;i++) {
            for (j = 0; j < 5; j++) {
                if (board[i][j].isIsPresent()) {
                    if (
                            (i > 0 && j > 0 && board[i - 1][j - 1].isIsPresent() && board[i][j].getDie().getColor().equals(board[i - 1][j - 1].getDie().getColor())) ||
                                    (i < 3 && j < 4 && board[i + 1][j + 1].isIsPresent() && board[i][j].getDie().getColor().equals(board[i + 1][j + 1].getDie().getColor())) ||
                                    (i > 0 && j < 4 && board[i - 1][j + 1].isIsPresent() && board[i][j].getDie().getColor().equals(board[i - 1][j + 1].getDie().getColor())) ||
                                    (i < 3 && j > 0 && board[i + 1][j - 1].isIsPresent() && board[i][j].getDie().getColor().equals(board[i + 1][j - 1].getDie().getColor()))
                            ) {
                        count++;
                        System.out.println("dadi addiacenti " + board[i][j].getDie().getColor());
                    }
                }
            }
        }

        return count;


    }

    @Override
    public int getPoints() {
        return 0;
    }
}
