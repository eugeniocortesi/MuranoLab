package it.polimi.ingsw.LM26.model.Cards.decorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class DrawOneMoreDie8 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public DrawOneMoreDie8(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }


    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play( int player){return false;}

    public boolean play (Die die, Box toBox, int pl) {


        Model model = singletonModel();
        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();
        PlaceDie placement = new PlaceDie(die, toBox, pl);
        PlayerZone player = model.getPlayerList().get(pl);
        if(!player.isSecondTurn() ){


        // ANCHE PIAZZAMENTO?

        if ( ! placement.placeDie()) {
                  System.out.println("error");
                  return false;
                  }
         else   {
            inDraft.remove(die);
            player.setSecondTurn(true);
            return true;
            }
        }
        return false;

    }
}
