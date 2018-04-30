package it.polimi.ingsw.LM26.Serialization;

public class Effect1 extends Effect{

    //Colore, Riga = true
    //Sfumature, Colonna = false

    private boolean colorShades;
    private boolean rowColomn;
    private String effect1=null;

    protected Effect1(boolean colorShades, boolean rowColomn) {

        this.colorShades = colorShades;
        this.rowColomn = rowColomn;
        this.type = "Effect1";
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

    protected void resolve() {

    }
}
