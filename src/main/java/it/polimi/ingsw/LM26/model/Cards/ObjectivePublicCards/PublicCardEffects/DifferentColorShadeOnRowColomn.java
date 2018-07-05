package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;

public class DifferentColorShadeOnRowColomn extends Effect {

    //Colore, Riga = true
    //Sfumature, Colonna = false

    private boolean colorShades;
    private boolean rowColomn;
    private String effect1=null;

    public DifferentColorShadeOnRowColomn(boolean colorShades, boolean rowColomn) {

        this.colorShades = colorShades;
        this.rowColomn = rowColomn;
        this.typeEffect = "DifferentColorShadeOnRowColomn";
        effect1="Different " + iscolorShades() + " - " + isrowColomn();
    }

    private String isrowColomn() {
        if (rowColomn)
        return "Rows";
        else return "Columns";
    }

    private String iscolorShades() {
        if (colorShades)
            return "Colors";
        else return "Shades";
    }

    public String getE() {
        return "Different " + iscolorShades() + " - " + isrowColomn();
    }

    public int checkEffect(WindowFramePlayerBoard b){

        Box[][] board=b.getBoardMatrix();
        boolean green1=false;
        boolean red2=false;
        boolean purple3=false;
        boolean blue4=false;
        boolean yellow5=false;
        boolean six=false;
        int count=0;
        boolean repeated=false;
        boolean incomplete=false;

        if(colorShades) {
            if (rowColomn) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].isIsPresent()) {
                            if (board[i][j].getDie().getColor() == Color.ANSI_BLUE)
                                if(!blue4) {
                                    blue4 = true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getColor() == Color.ANSI_RED)
                                if(!red2) {
                                    red2= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getColor() == Color.ANSI_YELLOW)
                                if(!yellow5) {
                                    yellow5= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getColor() == Color.ANSI_GREEN)
                                if(!green1) {
                                    green1= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getColor() == Color.ANSI_PURPLE)
                                if(!purple3) {
                                    purple3= true;
                                }
                                else repeated=true;
                        }else incomplete=true;
                    }
                    if (!repeated && !incomplete) count = count + 1;
                    green1 = false;
                    red2 = false;
                    purple3 = false;
                    blue4 = false;
                    yellow5 = false;
                    repeated=false;
                    incomplete=false;
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (board[j][i].isIsPresent()) {
                            if (board[j][i].getDie().getColor() == Color.ANSI_BLUE)
                                if(!blue4) {
                                    blue4 = true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getColor() == Color.ANSI_RED)
                                if(!red2) {
                                    red2= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getColor() == Color.ANSI_YELLOW)
                                if(!yellow5) {
                                    yellow5= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getColor() == Color.ANSI_GREEN)
                                if(!green1) {
                                    green1= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getColor() == Color.ANSI_PURPLE)
                                if(!purple3) {
                                    purple3= true;
                                }
                                else repeated=true;
                        }else incomplete=true;
                    }
                    if (!repeated && !incomplete) count = count + 1;
                    green1 = false;
                    red2 = false;
                    purple3 = false;
                    blue4 = false;
                    yellow5 = false;
                    repeated=false;
                    incomplete=false;
                }
            }
        }else {
            if (rowColomn) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].isIsPresent()) {
                            if (board[i][j].getDie().getValue()== 1)
                                if(!blue4) {
                                    blue4 = true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getValue() == 2)
                                if(!red2) {
                                    red2= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getValue() == 3)
                                if(!yellow5) {
                                    yellow5= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getValue() == 4)
                                if(!green1) {
                                    green1= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getValue() == 5)
                                if(!purple3) {
                                    purple3= true;
                                }
                                else repeated=true;
                            if (board[i][j].getDie().getValue()==6)
                                if(!six) {
                                    six= true;
                                }
                                else repeated=true;
                        }else incomplete=true;
                    }
                    if (!repeated && !incomplete) count = count + 1;
                    green1 = false;
                    red2 = false;
                    purple3 = false;
                    blue4 = false;
                    yellow5 = false;
                    six=false;
                    repeated=false;
                    incomplete=false;
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (board[j][i].isIsPresent()) {
                            if (board[j][i].getDie().getValue()== 1)
                                if(!blue4) {
                                    blue4 = true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getValue() == 2)
                                if(!red2) {
                                    red2= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getValue() == 3)
                                if(!yellow5) {
                                    yellow5= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getValue() == 4)
                                if(!green1) {
                                    green1= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getValue() == 5)
                                if(!purple3) {
                                    purple3= true;
                                }
                                else repeated=true;
                            if (board[j][i].getDie().getValue()==6)
                                if(!six) {
                                    six= true;
                                }
                                else repeated=true;
                        }else incomplete=true;
                    }
                    if (!repeated && !incomplete) count = count + 1;
                    green1 = false;
                    red2 = false;
                    purple3 = false;
                    blue4 = false;
                    yellow5 = false;
                    six=false;
                    repeated=false;
                    incomplete=false;
                }
            }

        }
        return count;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    protected void resolve() {

    }

    @Override
    public void rewrite() {

        this.typeEffect = "DifferentColorShadeOnRowColomn";

    }
}
