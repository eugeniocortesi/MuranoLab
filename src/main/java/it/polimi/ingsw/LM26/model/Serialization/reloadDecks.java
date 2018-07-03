package it.polimi.ingsw.LM26.model.Serialization;

import java.io.IOException;
import java.io.Serializable;

public class reloadDecks implements Serializable{



    public static Decks loadDecks(){

        Decks decks;

        decks = new Decks();

            try {
                decks.setup();
            } catch (IOException e) {
            }

            decks.serialize();


        return decks;
    }


}
