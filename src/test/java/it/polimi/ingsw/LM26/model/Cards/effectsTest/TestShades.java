package it.polimi.ingsw.LM26.model.Cards.effectsTest;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.Shades;
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

public class TestShades {

    Model model = singletonModel();
    WindowPatternCard pattern;
    WindowFramePlayerBoard board;
    ObjectivePublicCard shades1 = new ObjectivePublicCard(0, 2, new Shades(Elements.elements.LIGHT));
    ObjectivePublicCard shades2 = new ObjectivePublicCard(0, 2, new Shades(Elements.elements.MEDIUMS));
    ObjectivePublicCard shades3 = new ObjectivePublicCard(0, 2, new Shades(Elements.elements.DARK));
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
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(1);
        b[2][4].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(1);
        b[0][0].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(2);
        b[3][0].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(3);
        b[1][4].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(3);
        b[2][3].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(3);
        b[2][1].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[3][1].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[0][3].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[2][2].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[3][2].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(4);
        b[1][1].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(5);
        b[3][3].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(5);
        b[0][2].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(5);
        b[1][2].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);
        b[1][3].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);
        b[3][4].setDie(die);
        die = new Die(Color.ANSI_BLUE);
        die.setRoll(6);
        b[0][1].setDie(die);

        board.printCard();

        count1 = shades1.getRealEffect().checkEffect(board);
        count2 = shades2.getRealEffect().checkEffect(board);
        count3 = shades3.getRealEffect().checkEffect(board);
        if (count1 == 1) System.out.println("ok");
        else System.out.println("no: " + count1);
        if (count2 == 3) System.out.println("ok");
        else System.out.println("no: " + count2);
        if (count3 == 3) System.out.println("ok");
        else System.out.println("no: " + count3);

    }


}