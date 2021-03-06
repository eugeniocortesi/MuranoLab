package it.polimi.ingsw.LM26.model.PlayArea;

import java.io.Serializable;

/**
 * Color enumerative class
 * @author Eugenio Cortesi
 */

public enum Color implements Serializable {

    ANSI_RED("\u001B[31m"),

    ANSI_GREEN("\u001B[32m"),

    ANSI_YELLOW("\u001B[33m"),

    ANSI_BLUE("\u001B[34m"),

    ANSI_PURPLE("\u001B[35m"),

    WHITE("\u001B[37m");

    public static final String RESET = "\u001B[0m";

    private String escape;

    Color(String escape) {

        this.escape = escape;
    }

    public String escape() {

        return escape;
    }
}

