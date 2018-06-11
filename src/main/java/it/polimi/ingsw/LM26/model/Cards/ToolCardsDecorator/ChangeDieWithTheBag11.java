package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class ChangeDieWithTheBag11 implements ToolCardDecorator {

    private ToolCard toolcard = null;

    private boolean needPlacement=false;

    private DieInt die;

    private boolean firstPart=false;


    public ChangeDieWithTheBag11(ToolCard toolcard) {
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
    public boolean play(DieInt dieFromDraft, String inDeCrement){return false;}



    public boolean play( int player){return false;}

    public boolean play (DieInt dieFromDraft, int pl) {

        Model model = singletonModel();
        model.getBag().add(dieFromDraft);
        model.getDraftPool().remove(dieFromDraft);
        die = model.getBag().draw();
        System.out.println("you got a " + dieFromDraft.getColor() + " die ");
        firstPart=true;
        return true;

    }

    @Override
    public boolean play(int number, Box toBox, int pl) {

        if(!firstPart)return false;

        Model model = singletonModel();
        PlayerZone player=model.getPlayerList().get(pl);
        die.setRoll(number);
        PlaceDie placement = new PlaceDie(die, toBox, player);

        if (placement.placeDie()) {

            player.getPlayerBoard().incrementNumDice();
            player.getActionHistory().setDieUsed(true);
            player.getActionHistory().setPlacement(true);
            return true;
        }
        model.getDraftPool().addDie(die);
        needPlacement=true;
        return false;

    }


    public boolean isNeedPlacement() {
        return needPlacement;
    }

    public void setNeedPlacement(boolean needPlacement) {
        this.needPlacement = needPlacement;
    }

    public void noFirstPart() {
        this.firstPart = false;
    }

    public DieInt getDieCard11() {
        return die;
    }

    public void removeDie() { this.die = null; }
}