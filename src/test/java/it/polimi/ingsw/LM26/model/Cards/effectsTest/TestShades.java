package it.polimi.ingsw.LM26.model.Cards.effectsTest;

import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.ColoredDiagonals;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.DifferentColorShade;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.DifferentColorShadeOnRowColomn;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.Shades;
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

    public static class TestDifferentColorShadeOnRowColomn {

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

    public static class TestDifferentColorShade {

        Model model=singletonModel();
        WindowPatternCard pattern;
        WindowFramePlayerBoard board;
        ObjectivePublicCard cardShades = new ObjectivePublicCard(0, 5, new DifferentColorShade(true));
        ObjectivePublicCard cardColors = new ObjectivePublicCard(0, 4, new DifferentColorShade(false));
        int[] shades=new int[6];
        int minShades=20;
        int colors=18;

        @Test
        public void checkEffect() {

            model.reset();
            Random rand = new Random();
            int index = rand.nextInt( model.getDecks().getWindowPatternCardDeck().size());
            pattern = model.getDecks().getWindowPatternCardDeck().get(index);
            index = rand.nextInt(4);
            board = model.getDecks().getWindowFramePlayerBoardDeck().get(index);
            board.insertPatternIntoBoard(pattern.getWindowPatter());

            for(int i=0; i<4; i++)
                for (int j=0; j<5;j++){
                    DieInt die = model.getBag().draw();
                    die.roll();
                    board.getBoardMatrix()[i][j].setDie(die);
                    if(die.getValue()==1)shades[die.getValue()-1]++;
                    if(die.getValue()==2)shades[die.getValue()-1]++;
                    if(die.getValue()==3)shades[die.getValue()-1]++;
                    if(die.getValue()==4)shades[die.getValue()-1]++;
                    if(die.getValue()==5)shades[die.getValue()-1]++;
                    if(die.getValue()==6)shades[die.getValue()-1]++;
                }

            board.printCard();

            for(int i=0; i<shades.length;i++) {
                System.out.print(shades[i] + " ");
                if(shades[i]<minShades)minShades=shades[i];
            }
            System.out.println();


            if(18-model.getBag().getContBlue()<colors)colors=18-model.getBag().getContBlue();
            if(18-model.getBag().getContGreen()<colors)colors=18-model.getBag().getContGreen();
            if(18-model.getBag().getContPurple()<colors)colors=18-model.getBag().getContPurple();
            if(18-model.getBag().getContRed()<colors)colors=18-model.getBag().getContRed();
            if(18-model.getBag().getContYellow()<colors)colors=18-model.getBag().getContYellow();


            int result = cardColors.getRealEffect().checkEffect(board);
            System.out.println("setted "+colors+" complete set, found "+result);
            result=cardShades.getRealEffect().checkEffect(board);
            System.out.println("setted "+minShades+" complete set, found "+result);


        }

    }

    public static class TestColoredDiagonals {

        Model model=singletonModel();
        WindowPatternCard pattern;
        WindowFramePlayerBoard board;
        ObjectivePublicCard diagonals = new ObjectivePublicCard(0, new ColoredDiagonals());
        int result=0;

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
}