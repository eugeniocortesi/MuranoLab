package it.polimi.ingsw.LM26.PublicPlayerZone;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestToken {

    private Token newToken  = new Token(1);

    @Test
    public void checkToken() {


        newToken.decrementToken();
        newToken.decrementToken();

    }


}