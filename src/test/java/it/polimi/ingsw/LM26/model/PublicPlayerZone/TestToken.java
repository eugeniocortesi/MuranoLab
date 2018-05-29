package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import org.junit.Test;

public class TestToken {

    private Token newToken  = new Token(1);

    @Test
    public void checkToken() {

        System.out.println(newToken.getTokenNumber());
        newToken.decrementToken();
        System.out.println(newToken.getTokenNumber());
        newToken.decrementToken();

    }


}