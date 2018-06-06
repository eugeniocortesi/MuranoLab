package it.polimi.ingsw.LM26.view;

import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Bag;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DraftPool;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrack;
import it.polimi.ingsw.LM26.model.PlayArea.roundTrack.RoundTrackInt;
import it.polimi.ingsw.LM26.model.Serialization.Decks;
import it.polimi.ingsw.LM26.view.cli.ConsoleTools;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class TestConsoleTools {

    private Decks deck=singletonDecks();
    ConsoleTools cTools = new ConsoleTools();
    @Before
    public void setup(){
        Model model=new Model();
        DieInt d;
        DraftPool dPool= new DraftPool();
        RoundTrackInt rTrack= new RoundTrack();
        Bag bag = new Bag();
        ArrayList<DieInt> dList= new ArrayList<DieInt>();
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

        model.setRoundTrackInt(rTrack);
        ConsoleTools.setModel(model);
    }

    @Test
    public void TestRoundTrack(){
        cTools.printRoundTrack();
    }

    @Test
    public void TestSearchDie(){

    }

}
