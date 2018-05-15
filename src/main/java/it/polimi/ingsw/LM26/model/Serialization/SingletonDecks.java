package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.Decks;

import java.io.IOException;

public class SingletonDecks {

    private static Decks decks;



    public static Decks singletonDecks(){

        if(decks==null) {decks = new Decks();

            try {
                decks.setup();
            } catch (IOException e) {
            }

            decks.serialize();
        }

        return decks;
    }


}
