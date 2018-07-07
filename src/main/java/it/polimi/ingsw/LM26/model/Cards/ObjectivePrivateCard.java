package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Elements.elements;

import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.Serialization.Elements.elements.*;


/**
 * ObjectivePrivateCard class
 * @author Eugenio Cortesi
 */


public class ObjectivePrivateCard extends CardInt {

    private elements colour = null;

    private boolean inUse = false;

    private PlayerZone player;

    private int id;

    private transient static final Logger LOGGER = Logger.getLogger(ObjectivePrivateCard.class.getName());

    public ObjectivePrivateCard(){
    }

    public ObjectivePrivateCard(int id, elements colour) {

        this.colour = colour;

        this.id = id;

        this.typeCard = "ObjectivePrivateCard";

        LOGGER.setLevel(Level.ALL);
    }


    /**
     * method translates the card-information from the fle rider to the current information used in the game
     * @return color-type
     */

    public Color getColour() {

        if (colour == BLUE)

            return Color.ANSI_BLUE;

        else if (colour == VIOLET)

            return Color.ANSI_PURPLE;

        else if (colour == RED)

            return Color.ANSI_RED;

        else if (colour == GREEN)

            return Color.ANSI_GREEN;

        else if (colour == YELLOW)

            return Color.ANSI_YELLOW;

        else return null;
    }

    /**
     * method is used in the final phase. it adds up the value of the dice with the color of the specific private card
     * @param board player's board to do the computation on
     * @return result of the computation
     */

    public int checkPoints(WindowFramePlayerBoard board) {

        Box[][] b = board.getBoardMatrix();

        int count = 0;

        final int row=4;

        final int column=5;

        for (int i = 0; i < row; i++)

            for (int j = 0; j < column; j++)

                if (b[i][j].isIsPresent() && b[i][j].getDie().getColor().equals(this.getColour()))

                    count = count + b[i][j].getDie().getValue();

        return count;
    }

    public boolean isInUse() {

        return inUse;
    }

    public void setInUse(boolean inUse) {

        this.inUse = inUse;
    }

    public int getId() {

        return id;
    }

    public PlayerZone getPlayer() {

        return player;
    }


    /**
     * when a card is selected and assigned to a player, it is showed to him only and not stored.
     * After that each card stores the information about the owner and will not be sent again.
     * @param player owner of the card
     */

    public void setPlayer(PlayerZone player) {

        this.player = player;
    }

    public void printCard() {

        LOGGER.log(Level.INFO, "Private Card: "+ getId() + " "+ getColour());
    }

    @Override
    public void rewrite() {

        this.typeCard = "ObjectivePrivateCard";
    }
}
