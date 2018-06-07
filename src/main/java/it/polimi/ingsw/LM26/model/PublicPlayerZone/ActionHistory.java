package it.polimi.ingsw.LM26.model.PublicPlayerZone;

public class ActionHistory {

    private boolean dieUsed=false;

    private boolean firstTurn=false;

    private boolean secondTurn= false;

    private boolean placement=false;

    private boolean cardUsed=false;

    private boolean freezed=false;

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

    public boolean isPlacement() {
        return placement;
    }

    public void setPlacement(boolean diePlaced) {
        this.placement = diePlaced;
    }

    public boolean isCardUsed() {
        return cardUsed;
    }

    public void setCardUsed(boolean cardUsed) {
        this.cardUsed = cardUsed;
    }

    public boolean isDieUsed() { return dieUsed; }

    public void setDieUsed(boolean dieUsed) { this.dieUsed = dieUsed; }

    public boolean isFreezed() { return freezed; }

    public void setFreezed(boolean freezed) { this.freezed = freezed; }

    public void deleteTurnHistory() {

       dieUsed=false;

       placement=false;

       cardUsed=false;



    }

    public void deleteRoundHistory(){

        freezed= false;

        firstTurn=false;

        secondTurn= false;

        deleteTurnHistory();

    }


}