package it.polimi.ingsw.LM26.controller.controllerHandler;

import com.sun.org.apache.xpath.internal.operations.Mod;
import it.polimi.ingsw.LM26.controller.GamePhases.InitialPhase;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.OnBoardCards;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestEventChecker {

    Model model = singletonModel();
    EventChecker checker = new EventChecker(model);
    InitialPhase start;
    Random rand = new Random();
    int index=0;

    @Before

    public void setup() {

        model.reset();
        int i;
        for (i = 0; i < 2; i++)
            model.getPlayerList().add(new PlayerZone("name" + i, i));
        for (i = 0; i < 2; i++) {
            model.getPlayerList().get(i).setNumberPlayer(i);
            index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(index));
            System.out.println(model.getPlayerList().get(i).getWindowPatternCard().getToken());
        }

        start= new InitialPhase();
        start.setTokens();
    }

    @Test

   public void checkTokensAndCArd(){


        int token0=model.getPlayerList().get(0).getToken().getTokenNumber();
        int token1=model.getPlayerList().get(1).getToken().getTokenNumber();
        boolean result=false;
        System.out.println("tokens: "+token0);
        System.out.println("tokens: "+token1);

        //first usage of a card
        result=checker.checkToken(model.getPlayerList().get(0), model.getOnBoardCards().getToolCardList().get(0), true );
        assertTrue(result);

        model.getPlayer(0).getActionHistory().setCardUsed(true);
        model.getRestrictions().setFirstPart(false);

        assertFalse(checker.checkActionAvailability(model.getPlayer(0), model.getOnBoardCards().getToolCardList().get(0)));

        model.getPlayer(0).getActionHistory().setCardUsed(false);
        model.getRestrictions().setFirstPart(false);

        assertTrue(checker.checkActionAvailability(model.getPlayer(0), model.getOnBoardCards().getToolCardList().get(1)));


        model.getPlayer(0).getActionHistory().setCardUsed(true);
        model.getRestrictions().setFirstPart(true);

        assertTrue(checker.checkActionAvailability(model.getPlayer(0), model.getOnBoardCards().getToolCardList().get(2)));






        System.out.println(result + " after first time tokens: "+model.getPlayerList().get(0).getToken().getTokenNumber());
        assertEquals(token0-1, model.getPlayerList().get(0).getToken().getTokenNumber());

        //second usage the same card (it costs more)
        result=checker.checkToken(model.getPlayerList().get(1), model.getOnBoardCards().getToolCardList().get(0), true );
        assertTrue(result);
        System.out.println(result + " after second time tokens: "+model.getPlayerList().get(1).getToken().getTokenNumber());
        assertEquals(token1-2, model.getPlayerList().get(1).getToken().getTokenNumber());

        token1= model.getPlayerList().get(1).getToken().getTokenNumber();

        result=checker.checkToken(model.getPlayerList().get(1), model.getOnBoardCards().getToolCardList().get(0), true );
        if(model.getPlayerList().get(1).getWindowPatternCard().getToken() < 4 ) assertFalse(result);
            else assertTrue(result);

        token1= model.getPlayerList().get(1).getToken().getTokenNumber();
        if(token1>0) {
            System.out.println("at least one ");
            result = checker.checkToken(model.getPlayerList().get(1), model.getOnBoardCards().getToolCardList().get(1), true );
            assertTrue(result);
            assertEquals(token1 - 1, model.getPlayerList().get(1).getToken().getTokenNumber());
        }



        model.getOnBoardCards().getToolCardList().removeAll(model.getOnBoardCards().getToolCardList());
        assertEquals(0, model.getOnBoardCards().getToolCardList().size());

        model.getOnBoardCards().getToolCardList().add(model.getDecks().getToolCardDeck().get(1));
        model.getOnBoardCards().getToolCardList().add(model.getDecks().getToolCardDeck().get(3));
        model.getOnBoardCards().getToolCardList().add(model.getDecks().getToolCardDeck().get(8));

        for (int index = 1; index < 13; index++) {
            System.out.println(index);
            if (index == 2 || index == 4 || index == 9) assertTrue(checker.checkCard(index));
            else assertFalse(checker.checkCard(index));
        }



    }

}