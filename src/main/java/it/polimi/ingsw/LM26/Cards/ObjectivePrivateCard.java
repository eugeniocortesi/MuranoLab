package it.polimi.ingsw.LM26.Cards;

import java.util.ArrayList;
import it.polimi.ingsw.LM26.Serialization.Elements.elements;

import static it.polimi.ingsw.LM26.Serialization.Elements.elements.*;

public class ObjectivePrivateCard extends ObjectiveCardAC {


    private elements colour= null;
    int id;

    public ObjectivePrivateCard(int id, elements colour) {

        this.colour=colour;
        this.id=id;
    }
    public Color getColour() {

        if(colour==BLUE)
            return Color.ANSI_BLUE;

        else if(colour==VIOLET)
            return Color.ANSI_PURPLE;

        else if(colour==RED)
            return Color.ANSI_RED;

        else if(colour==GREEN)
            return Color.ANSI_GREEN;

        else if(colour==YELLOW)
            return Color.ANSI_YELLOW;

        else return null;
    }

    public int getId() {
        return id;
    }

}
