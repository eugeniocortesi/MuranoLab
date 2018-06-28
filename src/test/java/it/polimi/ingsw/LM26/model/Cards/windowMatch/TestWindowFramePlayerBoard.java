package it.polimi.ingsw.LM26.model.Cards.windowMatch;

import com.sun.org.apache.xpath.internal.operations.Mod;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;
import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class TestWindowFramePlayerBoard {

    private Decks decks = singletonDecks();
    Model model = singletonModel();
    WindowPatternCard pattern;
    WindowFramePlayerBoard board;

    @Test
    public void checkInsert(){

        decks.getWindowFramePlayerBoardDeck().get(1).insertPatternIntoBoard(decks.getWindowPatternCardDeck().get(1).getWindowPatter());
        decks.getWindowFramePlayerBoardDeck().get(1).printCard();

    }

    @Test
    public void checkEmptySpaces(){

        Random rand = new Random();
        int index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
        pattern = model.getDecks().getWindowPatternCardDeck().get(index);
        index = rand.nextInt(4);
        board = model.getDecks().getWindowFramePlayerBoardDeck().get(index);
        board.insertPatternIntoBoard(pattern.getWindowPatter());
        Box[][] b= board.getBoardMatrix();


        DieInt die;
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(1);
        b[2][4].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(1);
        b[0][0].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(2);
        b[3][0].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(3);
        b[1][4].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(3);
        b[2][3].setDie(die);

        board.printCard();

        if(board.getEmptySpaces()==(20-5)) System.out.println("ok");
        else System.out.println(board.getEmptySpaces());
    }
}