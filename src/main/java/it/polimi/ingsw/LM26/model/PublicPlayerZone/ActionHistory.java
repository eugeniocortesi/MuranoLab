package it.polimi.ingsw.LM26.model.PublicPlayerZone;

public class ActionHistory {

    private int diceAvailable= 2;

    private boolean firstTurn=false;

    private boolean secondTurn= false;

    private boolean diePlaced=false;

    private boolean cardUsed=false;

    public int getDiceAvailable() {
        return diceAvailable;
    }

    public void setDiceAvailable(int diceAvailable) {
        this.diceAvailable = diceAvailable;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public boolean isSecondTurn() {
        return secondTurn;
    }

    public void setSecondTurn(boolean secondTurn) {
        this.secondTurn = secondTurn;
    }

    public boolean isDiePlaced() {
        return diePlaced;
    }

    public void setDiePlaced(boolean diePlaced) {
        this.diePlaced = diePlaced;
    }

    public boolean isCardUsed() {
        return cardUsed;
    }

    public void setCardUsed(boolean cardUsed) {
        this.cardUsed = cardUsed;
    }

    public void dletePlayerHistory() {


       diceAvailable= 2;

       firstTurn=false;

       secondTurn= false;

       diePlaced=false;

       cardUsed=false;

    }


}