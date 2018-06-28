package it.polimi.ingsw.LM26.model.Serialization;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestColoredDiagonals {

    Model model=singletonModel();
    WindowPatternCard pattern;
    WindowFramePlayerBoard board;
    ObjectivePublicCard diagonals = new ObjectivePublicCard(0, new ColoredDiagonals());
    int result=0;

    @Test
    public void checkEffect() {

        Random rand = new Random();
        int index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
        pattern = model.getDecks().getWindowPatternCardDeck().get(index);
        index = rand.nextInt(4);
        board = model.getDecks().getWindowFramePlayerBoardDeck().get(index);
        board.insertPatternIntoBoard(pattern.getWindowPatter());
        Box[][] b = board.getBoardMatrix();


        DieInt die;
        die=new Die(Color.ANSI_BLUE);
        die.roll();
        b[0][0].setDie(die);
        die.roll();
        b[1][1].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.roll();
        b[2][0].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.roll();
        b[2][2].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.roll();
        b[3][3].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.roll();
        b[1][4].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.roll();
        b[2][3].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.roll();
        b[3][2].setDie(die);

        board.printCard();

        result=diagonals.getRealEffect().checkEffect(board);
        if(result==8)System.out.println("ok");
        else System.out.println("no: result is "+result);

    }

}