package it.polimi.ingsw.LM26.model.Cards.effectsTest;


import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCards.PublicCardEffects.DifferentColorShade;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestDifferentColorShade {

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