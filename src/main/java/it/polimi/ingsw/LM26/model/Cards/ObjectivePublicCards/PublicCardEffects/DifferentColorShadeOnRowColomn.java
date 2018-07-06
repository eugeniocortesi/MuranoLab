package it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;


/**
 * PublicCard effect class
 * @author Eugenio Cortesi
 */

public class DifferentColorShadeOnRowColomn extends Effect {

    private boolean colorShades;

    private boolean rowColomn;

    private boolean green1 = false;

    private  boolean red2 = false;

    private boolean purple3 = false;

    private boolean blue4 = false;

    private boolean yellow5 = false;

    private boolean six = false;

    private boolean repeated = false;

    private boolean incomplete = false;


    /**
     * Constructor
     * @param colorShades color->true, shade->false
     * @param rowColumn row->true, column->false
     */

    public DifferentColorShadeOnRowColomn(boolean colorShades, boolean rowColumn) {

        this.colorShades = colorShades;

        this.rowColomn = rowColumn;

        this.typeEffect = "DifferentColorShadeOnRowColomn";
    }

    private String isrowColomn() {

        if (rowColomn) return "Rows";

        else return "Columns";
    }

    private String iscolorShades() {

        if (colorShades) return "Colors";

        else return "Shades";
    }

    public String getE() {

        return "Different " + iscolorShades() + " - " + isrowColomn();
    }


    /**
     * the method is implemented for all the 4 combination and it read the matrix by rows and columns
     * -different colors in column
     * -differen colors in line
     * -different shades in column
     * -different shades in line
     * @param b board on which calculate points
     * @return points gained with this objective
     */

    public int checkEffect(WindowFramePlayerBoard b) {

        Box[][] board = b.getBoardMatrix();

        int count = 0;

        if (colorShades) {

            if (rowColomn) {

                for (int i = 0; i < 4; i++) {

                    for (int j = 0; j < 5; j++) {

                        if (board[i][j].isIsPresent()) {

                            if (board[i][j].getDie().getColor() == Color.ANSI_BLUE)

                                if (!blue4) { blue4 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getColor() == Color.ANSI_RED)

                                if (!red2) { red2 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getColor() == Color.ANSI_YELLOW)

                                if (!yellow5) { yellow5 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getColor() == Color.ANSI_GREEN)

                                if (!green1) { green1 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getColor() == Color.ANSI_PURPLE)

                                if (!purple3) { purple3 = true;

                                } else repeated = true;

                        } else incomplete = true;
                    }

                    if (!repeated && !incomplete) count = count + 1;

                   resetParameters();
                }
            }

            else { for (int i = 0; i < 5; i++) {

                    for (int j = 0; j < 4; j++) {

                        if (board[j][i].isIsPresent()) {

                            if (board[j][i].getDie().getColor() == Color.ANSI_BLUE)

                                if (!blue4) { blue4 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getColor() == Color.ANSI_RED)

                                if (!red2) { red2 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getColor() == Color.ANSI_YELLOW)

                                if (!yellow5) { yellow5 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getColor() == Color.ANSI_GREEN)

                                if (!green1) { green1 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getColor() == Color.ANSI_PURPLE)

                                if (!purple3) { purple3 = true;

                                } else repeated = true;

                        } else incomplete = true;
                    }

                    if (!repeated && !incomplete) count = count + 1;

                    resetParameters();
                }
            }
        }

        else { if (rowColomn) {

                for (int i = 0; i < 4; i++) {

                    for (int j = 0; j < 5; j++) {

                        if (board[i][j].isIsPresent()) {

                            if (board[i][j].getDie().getValue() == 1)

                                if (!blue4) { blue4 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getValue() == 2)

                                if (!red2) { red2 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getValue() == 3)

                                if (!yellow5) { yellow5 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getValue() == 4)

                                if (!green1) { green1 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getValue() == 5)

                                if (!purple3) { purple3 = true;

                                } else repeated = true;

                            if (board[i][j].getDie().getValue() == 6)

                                if (!six) { six = true;

                                } else repeated = true;

                        } else incomplete = true;
                    }

                    if (!repeated && !incomplete) count = count + 1;

                    resetParameters();
                }
            }

            else { for (int i = 0; i < 5; i++) {

                    for (int j = 0; j < 4; j++) {

                        if (board[j][i].isIsPresent()) {

                            if (board[j][i].getDie().getValue() == 1)

                                if (!blue4) { blue4 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getValue() == 2)

                                if (!red2) { red2 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getValue() == 3)

                                if (!yellow5) { yellow5 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getValue() == 4)

                                if (!green1) { green1 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getValue() == 5)

                                if (!purple3) { purple3 = true;

                                } else repeated = true;

                            if (board[j][i].getDie().getValue() == 6)

                                if (!six) { six = true;

                                } else repeated = true;

                        } else incomplete = true;
                    }

                    if (!repeated && !incomplete) count = count + 1;

                    resetParameters();
                }
            }

        }
        return count;
    }

    private void resetParameters(){

        green1 = false;

        red2 = false;

        purple3 = false;

        blue4 = false;

        yellow5 = false;

        six = false;

        repeated = false;

        incomplete = false;
    }

    @Override
    public void rewrite() {

        this.typeEffect = "DifferentColorShadeOnRowColomn";
    }
}
