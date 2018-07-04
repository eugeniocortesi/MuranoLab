package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.GamePhases.CentralPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.Game;
import it.polimi.ingsw.LM26.controller.GamePhases.InitialPhase;
import it.polimi.ingsw.LM26.controller.GamePhases.PhaseInt;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestChangeDieFromDraftToRoundTrack5 {

    Model model;
    Random rand = new Random();

    @Before

    public void setup(){

        model= singletonModel();
        model.reset();

        int i;
        int index = 0;
        for (i = 0; i < 2; i++)
            model.getPlayerList().add(new PlayerZone("name" + i, i));
        for (i = 0; i < 2; i++) {
            model.getPlayerList().get(i).setNumberPlayer(i);
            index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
            model.getPlayerList().get(i).setWindowPatternCard(model.getDecks().getWindowPatternCardDeck().get(index));
            model.getPlayerList().get(i).setPlayerBoard(model.getDecks().getWindowFramePlayerBoardDeck().get(i));
            model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        }
        Game game = new Game();
        InitialPhase initialPhase=new InitialPhase();
        initialPhase.doAction(game);
        PhaseInt cf= game.getPhase();
        cf.nextRound(cf.getCurrentRound(), game);
        model.getDraftPool().printDraftPool();

        RoundTrack rt = new RoundTrack("s");

        ArrayList<DieInt> list = new ArrayList<DieInt>();
        for (int h=0; h<2; h++){

            for(int j=0; j<5;j++){
                DieInt die =model.getBag().draw();
                die.roll();
                list.add(die);
            }
            rt.addDice(list);
            list.removeAll(list);
            rt.update();
        }
        rt.dump();
        model.setRoundTrackInt(rt);
    }

    @Test
    public void checkEffect(){

        DieInt fromDraft= model.getDraftPool().get(2);
        DieInt fromRoundTrack= model.getRoundTrackInt().getRoundTrackTurn(2).get(4);

        model.getDecks().getToolCardDeck().get(4).play(fromDraft, fromRoundTrack);


        assertEquals(fromDraft, model.getRoundTrackInt().getRoundTrackTurn(2).get(4));
        assertEquals(fromRoundTrack, model.getDraftPool().get(2));



    }

}