package it.polimi.ingsw.LM26.controller;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.*;

public class TestPlaceDie {

    Model model;
    Random rand = new Random();
    DieInt die=null;
    PlaceDie placement=null;
    Box[][] b=null;
    PlayerZone player;
    int i= 0, j= 0 ;
    int attempt=0;
    int dieCount=0;
    int[][] values=new int[4][5];

    @Before

    public void setup() {

        model= singletonModel();
        model.reset();

        model.getPlayerList().add(new PlayerZone("name" + i, i));
        model.getPlayerList().get(i).setNumberPlayer(i);
        int index = rand.nextInt(model.getDecks().getWindowPatternCardDeck().size());
        WindowPatternCard pattern = model.getDecks().getWindowPatternCardDeck().get(index);
        model.getPlayerList().get(i).setWindowPatternCard(pattern);
        WindowFramePlayerBoard board=model.getDecks().getWindowFramePlayerBoardDeck().get(0);
        model.getPlayerList().get(i).setPlayerBoard(board);
        model.getPlayerList().get(i).getPlayerBoard().insertPatternIntoBoard(model.getPlayerList().get(i).getWindowPatternCard().getWindowPatter());
        b=model.getPlayerList().get(i).getPlayerBoard().getBoardMatrix();
        player=model.getPlayerList().get(i);
        player.getPlayerBoard().printCard();
    }

   /* @Test

    public void checkPlacement(){

         while (dieCount<20 ) {
            if (model.getBag().size() == 0) model.setBag(new Bag());
            die = model.getBag().draw();
            die.roll();

            //first placement

            i = rand.nextInt(4);
            j = rand.nextInt(5);
            System.out.println("first placement: " + player.getPlayerBoard().isEmpty());
            System.out.println("attempt: " + attempt + " whit: " + i + " and " + j);
            die.dump();
            //controlling box was empty
            if (!b[i][j].isIsPresent()) System.out.println("box was empty");
            placement = new PlaceDie(die, b[i][j], player);

            while (!placement.placeDie() && attempt < 1000) {
                attempt++;
                i = rand.nextInt(4);
                j = rand.nextInt(5);
                if (model.getBag().size() == 0) model.setBag(new Bag());
                die = model.getBag().draw();
                die.roll();
                System.out.println("attempt: " + attempt + " whit: " + i + " and " + j);
                die.dump();

                placement = new PlaceDie(die, b[i][j], player);


                if (b[i][j].isIsPresent()) {
                    if (b[i][j].getDie().getValue() != values[i][j]) {
                        System.out.println("IL DADO " + i + " " + j + " HA CAMBIATO VALORE DA " + values[i][j] + " A " + b[i][j].getDie().getValue());
                        values[i][j] = b[i][j].getDie().getValue();
                    }
                }
            }

            if (attempt < 1000) {
                values[i][j] = b[i][j].getDie().getValue();


                System.out.println("Placed: are all restrictions respected?");
                if (!b[i][j].isIsPresent()) System.out.println("ERROR: die not setted");
                if (dieCount == 1 && i != 0 && i != 3 && j != 0 && j != 4) System.out.println("ERROR: not on the edge");
                if (b[i][j].getPatternBox().isShade() && b[i][j].getPatternBox().getValue() != die.getValue())
                    System.out.println("ERROR: value non respected ");
                if (b[i][j].getPatternBox().isColor() && !b[i][j].getPatternBox().getColor().equals(Color.WHITE) && !b[i][j].getPatternBox().getColor().equals(die.getColor()))
                    System.out.println("ERROR: color non respected ");

                //controllare che il dado sia almeno vicino ad un'altro


                if (j < 4) {
                    if ((b[i][j + 1].isIsPresent() && (b[i][j].getDie().getColor().equals(b[i][j + 1].getDie().getColor()) || b[i][j].getDie().getValue() == b[i][j + 1].getDie().getValue())))
                        System.out.println("                                         error: near a die with same color/value DX");
                }
                if (j > 0) {
                    if ((b[i][j - 1].isIsPresent() && (b[i][j].getDie().getColor().equals(b[i][j - 1].getDie().getColor()) || b[i][j].getDie().getValue() == b[i][j - 1].getDie().getValue())))
                        System.out.println("                                         error: near a die with same color/value SX ");
                }
                if (i < 3) {
                    if ((b[i + 1][j].isIsPresent() && (b[i][j].getDie().getColor().equals(b[i + 1][j].getDie().getColor()) || b[i][j].getDie().getValue() == b[i + 1][j].getDie().getValue())))
                        System.out.println("                                         error: near a die with same color/value DOWN  ");
                }
                if (i > 0) {
                    if ((b[i - 1][j].isIsPresent() && (b[i][j].getDie().getColor().equals(b[i - 1][j].getDie().getColor()) || b[i][j].getDie().getValue() == b[i - 1][j].getDie().getValue())))
                        System.out.println("                                         error: near a die with same color/value UP ");
                }


            }

            dieCount++;
            attempt = 0;
            player.getPlayerBoard().printCard();

        }
        player.getWindowPatternCard().printCard();

    }

*/
}