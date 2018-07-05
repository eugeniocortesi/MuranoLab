package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Serialization.Decks;

import java.io.IOException;
import java.io.Serializable;

public class reloadDecks implements Serializable{



    public static Decks loadDecks(){

        Decks decks;

        decks = new Decks();


                decks.setup();


                decks.serialize();


        return decks;
    }


}
