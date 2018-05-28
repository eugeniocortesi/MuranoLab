package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class RollAllDraftDice7 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public RollAllDraftDice7(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player ){return false;}
    public boolean play(Die dieFromDraft, Box toBox, int player){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}

    public boolean play (int pl ) {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();

        if(player.getActionHistory().getDiceAvailable()==1 && player.getActionHistory().isFirstTurn()==false ) {

            for (int i = 0; i < inDraft.size(); i++)
                inDraft.get(i).roll();
            return true;
        }

        else System.out.println("you can't use this card now");

        return false;

    }
}
//player.isDieUsed()==false