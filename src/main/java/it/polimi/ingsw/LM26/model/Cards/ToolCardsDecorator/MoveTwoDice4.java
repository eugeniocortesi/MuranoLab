package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.Die;

public class MoveTwoDice4 implements ToolCardDecorator {

    private ToolCard toolcard = null;


    public MoveTwoDice4(ToolCard toolcard) {
        this.toolcard = toolcard;
    }

    public int getNum(){
        return toolcard.getNum();
    }

    public void printCard(){
        toolcard.printCard();
    }

    public boolean play(Box fromBox, Box toBox, int player){return false;}
    public boolean play(Die dieFromDraft, Box toBox, int player){return false;}
    public boolean play(Die dieFromDraft, Die dieFromRoundTrack){return false;}
    public boolean play( Die dieFromDraft, String inDeCrement){return false;}
    public boolean play(Die dieFromDraft){return false;}
    public boolean play(int player ){return false;}

    public boolean play (Box fromBox1, Box toBox1, Box fromBox2, Box toBox2, int player ) {


        Die die = (Die) fromBox1.getDie();
        Die die2 = (Die)fromBox2.getDie();
        PlaceDie placement = new PlaceDie(die, toBox1, player);

        if(!(placement.placeDie())){
                       System.out.println("error " + 1);
                       return false;
                   }

        placement = new PlaceDie(die2, toBox2, player);
        if(!(placement.placeDie())){
                        System.out.println("error " + 2);
                        toBox1.free();
                        return false;

                    }
        fromBox1.free();
        fromBox2.free();
        return true;

    }
}
