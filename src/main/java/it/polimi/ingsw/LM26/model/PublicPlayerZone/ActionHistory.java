package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import java.io.Serializable;

/**
 * Action History class
 * @author Eugenio
 * class that keeps track of the clients moves ( usage of a card or placement of a die),
 * store his location in the turn(first turn -> right order, second turn -> reverse order),
 * and if he can contune the turn or he is freezed in the current one, beacuse of oreviusly restrictive action,
 * so that future actions can be allawoe or denied.
 * every Actionhistory object is specific of a client
 */

public class ActionHistory implements Serializable {

    private boolean dieUsed = false;

    private boolean firstTurn = false;

    private boolean secondTurn = false;

    private boolean placement = false;

    private boolean cardUsed = false;

    private boolean freezed = false;

    private boolean jump = false;

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

    public boolean isDieUsed() {
        return dieUsed;
    }

    public void setDieUsed(boolean dieUsed) {
        this.dieUsed = dieUsed;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public void setFreezed(boolean freezed) {
        this.freezed = freezed;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }


    /**
     * part of the information is reset at the end of the client's turn
     */

    public void deleteTurnHistory() {

        dieUsed = false;

        placement = false;

        cardUsed = false;

        jump = false;
    }


    /**
     * all the client's information is reset at the end of the round
     */

    public void deleteRoundHistory() {

        freezed = false;

        firstTurn = false;

        secondTurn = false;

        deleteTurnHistory();
    }
}