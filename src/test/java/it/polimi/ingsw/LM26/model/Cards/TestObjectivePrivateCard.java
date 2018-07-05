package it.polimi.ingsw.LM26.model.Cards;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.Serialization.Elements;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class TestObjectivePrivateCard {

    Model model = singletonModel();
    WindowPatternCard pattern;
    WindowFramePlayerBoard board;
    ObjectivePrivateCard card=new ObjectivePrivateCard(0, Elements.elements.YELLOW);
    int count1, count2, count3;

    @Test
    public void checkEffect() {

        model.reset();
        Random rand = new Random();
        int index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
        pattern = model.getDecks().getWindowPatternCardDeck().get(index);
        index = rand.nextInt(4);
        board = model.getDecks().getWindowFramePlayerBoardDeck().get(index);
        board.insertPatternIntoBoard(pattern.getWindowPatter());
        Box[][] b = board.getBoardMatrix();


        DieInt die;
        die=new Die(Color.ANSI_YELLOW);
        die.setRoll(1);
        b[2][4].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.setRoll(3);
        b[0][0].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.setRoll(6);
        b[3][0].setDie(die);
        die=new Die(Color.ANSI_YELLOW);
        die.setRoll(4);
        b[1][4].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[0][3].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[2][2].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[3][2].setDie(die);
        die=new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[1][1].setDie(die);
        die=new Die(Color.ANSI_RED);
        die.setRoll(5);
        b[3][3].setDie(die);
        die=new Die(Color.ANSI_RED);
        die.setRoll(5);
        b[0][2].setDie(die);
        die=new Die(Color.ANSI_PURPLE);
        die.setRoll(5);
        b[1][2].setDie(die);

        board.printCard();

        int result=card.checkPoints(board);
        if(result==14)System.out.println("ok "+ result);
        else System.out.println(result);
    }



}