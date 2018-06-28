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

public class TestDifferentColorShadeOnRowColomn {

    Model model=singletonModel();
    WindowPatternCard pattern;
    WindowFramePlayerBoard board;
    //Colore, Riga = true
    //Sfumature, Colonna = false
    ObjectivePublicCard colomnShades = new ObjectivePublicCard(0, 4, new DifferentColorShadeOnRowColomn(false, false));
    ObjectivePublicCard colomnColors = new ObjectivePublicCard(0, 5, new DifferentColorShadeOnRowColomn(true, false));
    ObjectivePublicCard rowShades = new ObjectivePublicCard(0, 5, new DifferentColorShadeOnRowColomn(false,true));
    ObjectivePublicCard rowColors = new ObjectivePublicCard(0, 6, new DifferentColorShadeOnRowColomn(true, true));

    @Test
    public void checkEffect() {

        Random rand = new Random();
        int index = rand.nextInt( model.getDecks().getWindowPatternCardDeck().size());
        pattern = model.getDecks().getWindowPatternCardDeck().get(index);
        index = rand.nextInt(4);
        board = model.getDecks().getWindowFramePlayerBoardDeck().get(index);
        board.insertPatternIntoBoard(pattern.getWindowPatter());
        Box[][] b = board.getBoardMatrix();

        DieInt die;
        die= new Die();
        die.setColor(Color.ANSI_RED);
        die.setRoll(1);
        b[0][0].setDie(die);

        die= new Die();
        die.setColor(Color.ANSI_BLUE);
        die.setRoll(2);
        b[0][1].setDie(die);
        die= new Die();
        die.setColor(Color.ANSI_GREEN);
        die.setRoll(3);
        b[0][2].setDie(die);

        die= new Die();
        die.setColor(Color.ANSI_YELLOW);
        die.setRoll(4);
        b[0][3].setDie(die);

        die= new Die();
        die.setColor(Color.ANSI_PURPLE);
        die.setRoll(5);
        b[0][4].setDie(die);


        die= new Die();
        die.setColor(Color.ANSI_RED);
        die.setRoll(4);
        b[1][4].setDie(die);

        die= new Die();
        die.setColor(Color.ANSI_YELLOW);
        die.setRoll(3);
        b[2][4].setDie(die);

        die= new Die();
        die.setColor(Color.ANSI_GREEN);
        die.setRoll(2);
        b[3][4].setDie(die);


        board.printCard();

        int result;

        result= colomnShades.getRealEffect().checkEffect(board);
        if(result==1)
        System.out.println("sfumatura su colonne ok" );

        result= colomnColors.getRealEffect().checkEffect(board);
        if(result==1)
        System.out.println("colori su colonna ok");

        result= rowColors.getRealEffect().checkEffect(board);
        if(result==1)
        System.out.println("clori sulla riga ok");

        result= rowShades.getRealEffect().checkEffect(board);
        if(result==1)
        System.out.println("sfumature sulla riga ok");






    }

}