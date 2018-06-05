package it.polimi.ingsw.LM26.model.PublicPlayerZone;

import org.junit.Test;

public class TestActionHistory {

    ActionHistory action = new ActionHistory();

    @Test
    public void checkActionReset(){
        action.deletePlayerHistory();
        if(action.isDieUsed()==false && action.isFirstTurn()==false && action.isSecondTurn()==false && action.isPlacement()==false &&
                action.isCardUsed()==false) System.out.println("ok");
    }

}