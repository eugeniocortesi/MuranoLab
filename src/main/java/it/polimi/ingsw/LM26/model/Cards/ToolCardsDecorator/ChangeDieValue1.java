package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

public class ChangeDieValue1 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public ChangeDieValue1(ToolCard toolcard) {
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

    @Override
    public boolean isInUse() {
        return false;
    }

    @Override
    public void setInUse(boolean inUse) {

    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player){return false;}
    public boolean play(DieInt dieFromDraft, Box toBox, int player){return false;}
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack){return false;}
    public boolean play(DieInt dieFromDraft, int pl){return false;}
    public boolean play( int player){return false;}

    public boolean play (DieInt die, String inDeCrement) {


        if (inDeCrement == "increment") {
            if (die.getValue() == 6) {
                System.out.println("error, choose a lower value");
                return false;
            }
            die.increment();
            die.dump();
            return true;
        }

        if (inDeCrement == "decrement"){
            if (die.getValue() == 1) {
                System.out.println("error, choose a higher value");
                return false;
            }
            die.decrement();
            die.dump();
            return true;
        }

        return false;


    }
}
