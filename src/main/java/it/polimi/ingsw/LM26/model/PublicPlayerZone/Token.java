package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Token class
 * @author Eugenio Cortesi
 */

public class Token implements Serializable {

    private int tokens;

    private transient static final Logger LOGGER = Logger.getLogger(Token.class.getName());

    /**
     * player's tokens are assigned in initialPhase, and correspond to the token of the player's board.
     * @param token number of tokens to assign to a player
     */

    public Token(int token) {

        this.tokens = token;

        LOGGER.setLevel(Level.ALL);
    }

    public int getTokenNumber() {

        return tokens;
    }

    public void decrementToken() {

        if (tokens == 0)

            LOGGER.log(Level.INFO, "Impossible, player has 0 tokens");

        else tokens--;
    }
}