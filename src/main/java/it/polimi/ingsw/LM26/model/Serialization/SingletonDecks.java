package it.polimi.ingsw.LM26.model.Serialization;

import java.io.IOException;
import java.io.Serializable;

public class SingletonDecks implements Serializable{

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
