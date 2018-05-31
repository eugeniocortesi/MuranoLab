package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveWithNoValueRestriction3 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveWithNoValueRestriction3(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public int getToken(){
        return toolcard.getToken();
    }

    public void setOneToken(PlayerZone player){}

    public void setTwoToken(PlayerZone player){}

    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play( DieInt dieFromDraft, String inDeCrement){return false;}
    public boolean play(DieInt dieFromDraft){return false;}
    public boolean play(int player){return false;}

    public boolean play (Box fromBox,Box toBox, int pl)  {

        Model model = singletonModel();
        PlayerZone player = model.getPlayerList().get(pl);
        DieInt die = fromBox.getDie();
        if(!fromBox.isIsPresent())return false;                      //added controller
        PlaceDie placement = new PlaceDie(die, toBox, player);
        fromBox.free();
        if(player.getPlayerBoard().getNumDice()==1) {
            if (placement.checkColorRestriction() && placement.checkEdgeRestrictions()) {
                toBox.setDie(die);
                return true;
            }
        }
        else if (placement.checkColorRestriction() && placement.checkNearByRestrictions() ){

            return true;
        }

        System.out.println("error card 2");
        fromBox.setDie(die);
        return false;

    }
}
