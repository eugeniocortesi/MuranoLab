package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;

public class DifferentColorShadeOnRowColomn extends Effect{

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
        if (rowColomn==true)
        return "Rows";
        else return "Columns";
    }

    private String iscolorShades() {
        if (colorShades==true)
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

        return 0;


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
