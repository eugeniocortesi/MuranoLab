package it.polimi.ingsw.LM26.model.PlayArea;

import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;


/**
 * Model Restrictions class
 * @author Eugenio Cortesi
 * class the stores all the information about what the game has to impose next to the client playing
 * forinstance:
 * - after card 6, 8, 11 if the placement whit the specific die fails, the die must be stored and a boolean informs
 *      that the mext attemp must be done with the stored die.
 * - card 11 impone to remain in the player's turn until he do the second parte of the card-action.
 * - card 11 must comunicates to client the color of the extracted die.
 * - card 8 let the client do one more placement, the restriction information comunicates if the placement-action th regular one or the last allowed be the card.
 */

public class Restrictions {

    private boolean tool8needPlacement = false;

    private boolean needPlacement = false;

    private DieInt die = null;

    private boolean firstPart = false;

    private boolean currentPlacement = false;

    private String color = null;


    /**
     * each restriction is reset at the and of a client's turn
     */

    public void resetRestrictions() {

        tool8needPlacement = false;

        needPlacement = false;

        die = null;

        firstPart = false;

        currentPlacement = false;

        color = null;
    }

    public boolean isTool8needPlacement() {

        return tool8needPlacement;
    }

    public void setTool8needPlacement(boolean tool8needPlacement) {

        this.tool8needPlacement = tool8needPlacement;
    }

    public boolean isNeedPlacement() {

        return needPlacement;
    }

    public void setNeedPlacement(boolean needPlacement) {

        this.needPlacement = needPlacement;
    }

    public DieInt getDie() {

        return die;
    }

    public void setDie(DieInt die) {

        this.die = die;
    }

    public boolean isFirstPart() {

        return firstPart;
    }

    public void setFirstPart(boolean firstPart) {

        this.firstPart = firstPart;
    }

    public boolean isCurrentPlacement() {

        return currentPlacement;
    }

    public void setCurrentPlacement(boolean currentPlacement) {

        this.currentPlacement = currentPlacement;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }
}