package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.view.cli.ConsoleTools;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;
import static org.junit.Assert.assertEquals;

public class TestConsoleTools {

    private Model model=singletonModel();


    private Decks decks;
    private ConsoleTools cTools = new ConsoleTools();
    private ArrayList<DieInt> dList= new ArrayList<DieInt>();
    private ArrayList<DieInt> dieList= new ArrayList<DieInt>();
    @Before
    public void setup(){
        model.reset();
        decks=model.getDecks();
        DieInt die=new Die(Color.ANSI_RED);
        DieInt die2=new Die(Color.ANSI_GREEN);
        DieInt die3=new Die(Color.ANSI_RED);
        die.roll();
        die2.roll();
        die3.roll();
        for(int i=0; i<5; i++){
            if(die.getNumber()!=6){
                die.increment();
            }
            if(die3.getNumber()!=6){
                die3.increment();
            }
            if(die2.getNumber()!=1){
                die2.decrement();
            }
        }
        dieList.add(die);
        dieList.add(die2);
        die3.decrement();
        dieList.add(die3);

        DieInt d;
        DraftPool dPool= new DraftPool("s");
        RoundTrackInt rTrack= new RoundTrack("s");
        Bag bag = new Bag();
        for(int j=0; j<5; j++){
            d= bag.draw();
            d.roll();
           dList.add(d);
        }
        for(int i=0; i<4; i++){
            rTrack.addDice(dList);
        }
        for(int s=0; s<5; s++){
            dList.remove(0);
        }
        rTrack.addDice(dList);
        for(int j=0; j<5; j++){
            d= bag.draw();
            d.roll();
            dList.add(d);
        }
        for(int f=0; f<5; f++){
            rTrack.addDice(dList);
        }
        //roundtrack with an empty turn
        model.setRoundTrackInt(rTrack);
        dPool.setInDraft(dieList);
        model.setDraftPool(dPool);
        ConsoleTools.setModel(model);
    }

    @Test
    public void TestRoundTrack(){
        cTools.printRoundTrack();
    }

    @Test
    //list of three dice: R6, VE1, R5
    public void TestSearchDie(){
        for(DieInt j : dieList){
            System.out.println(j);
        }
        String s="R6";
        char[] chars=s.toCharArray();
        int n=cTools.searchDie(chars, dieList);
        assertEquals( 0, n);
        String st="VE1";
        chars=st.toCharArray();
        n=cTools.searchDie(chars, dieList);
        assertEquals(1, n);
    }

    @Test
    //empty list=-2, wrong input=-1
    public void testSearchFailed(){
        ArrayList<DieInt> dieL= new ArrayList<>();
        String s="G3";
        char[] cArray=s.toCharArray();
        int num=cTools.searchDie(cArray, dieL);
        assertEquals(-2, num);
        s="e3"; cArray=s.toCharArray();
        num=cTools.searchDie(cArray,dieList);
        assertEquals(-1, num);
    }

    @Test
    public void testWrongDieRightSemantic(){
        for(DieInt j : dieList){
            System.out.println(j);
        }
        String s="R3";
        char[] chars=s.toCharArray();
        int n=cTools.searchDie(chars, dieList);
        assertEquals( -1, n);
    }

    @Test
    public void testSearchInDraftPool(){
        String s="R6";
        for(DieInt j : dieList){
            System.out.println(j);
            System.out.println(j.getNumber());
        }
        char[] chars=s.toCharArray();
        int n=cTools.searchDraftPoolDie(chars);
        assertEquals( 0, n);
    }

    @Test
    //same 6 arraylists and one with a R2 die
    public void testSearchInRoundTrack(){
        DieInt die4=new Die(Color.ANSI_RED);
        die4.roll();
        for(int i=0; i<5; i++){
            if(die4.getNumber()!=1) die4.decrement();
        }
        die4.increment();
        ArrayList<DieInt> searched= new ArrayList<DieInt>();
        searched.add(die4);
        RoundTrackInt rt=new RoundTrack("s");
       for(int j=0; j<6; j++){
           rt.addDice(dieList);
       }
       rt.addDice(searched);

       model.setRoundTrackInt(rt);
       ConsoleTools.setModel(model);
       cTools.printRoundTrack();
        String s="R2";
        char[] chars=s.toCharArray();
        int[] n=cTools.searchRoundTrackDie(chars);
        int[] expected={0,6};
        assertEquals( expected[1], n[1]);
        assertEquals( expected[0], n[0]);
    }
}
