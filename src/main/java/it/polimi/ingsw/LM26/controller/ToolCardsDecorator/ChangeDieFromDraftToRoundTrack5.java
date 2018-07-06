package it.polimi.ingsw.LM26.controller.ToolCardsDecorator;

import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;


/**
 * ToolCard decorator class
 * @author Eugenio Cortesi
 */

public class ChangeDieFromDraftToRoundTrack5 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    private static final Logger LOGGER = Logger.getLogger(ChangeDieFromDraftToRoundTrack5.class.getName());

    public ChangeDieFromDraftToRoundTrack5() {
    }

    public ChangeDieFromDraftToRoundTrack5(ToolCard toolcard) {

        this.toolcard = toolcard;

        LOGGER.setLevel(Level.ALL);

        this.type="ChangeDieFromDraftToRoundTrack5";

        this.typeToolCard = "ToolCard";
    }


    /**
     * it exchanges selected die
     * @param die1 die from draft pool selected by client for the action
     * @param die2 die from round track selected by client for the action
     * @return the success of the card usage
     */

    @Override
    public boolean play (DieInt die1, DieInt die2) {

        Model model = singletonModel();

        ArrayList<DieInt> inDraft = model.getDraftPool().getInDraft();

        int index=0;

        if( die1== null || die2==null ) return false;

        int size=model.getRoundTrackInt().getRoundTrackTurnList().size();

        if(size==0) {

            LOGGER.log(Level.INFO,"no dice in roundTrack");

            return false;
        }

        for(int i=0; i<size;i++) {

            ArrayList<DieInt> diceList = model.getRoundTrackInt().getRoundTrackTurnList().get(i).getDiceList();

            index = diceList.indexOf(die2);

            if (index != -1) {

                inDraft.add(inDraft.indexOf(die1), die2);

                diceList.add(diceList.indexOf(die2), die1);

                inDraft.remove(die1);

                diceList.remove(die2);

                return true;
            }
        }

        return false;
    }

    @Override
    public int getNum(){

        return toolcard.getNum();
    }

    @Override
    public void printCard(){

        toolcard.printCard();
    }

    @Override
    public int getToken(){

        return toolcard.getToken();
    }

    @Override
    public void setOneToken(PlayerZone player){

        toolcard.setOneToken(player);
    }

    @Override
    public void setTwoToken(PlayerZone player){

        toolcard.setTwoToken(player);
    }

    @Override
    public boolean isInUse() {

        return toolcard.isInUse();
    }

    @Override
    public void setInUse(boolean inUse) {

        toolcard.setInUse(inUse);
    }

    /**
     * method that rewrite type for serializing with gson
     */

    @Override
    public void rewrite() {

        this.type="ChangeDieFromDraftToRoundTrack5";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play(Box fromBox, Box toBox,  PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox,PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play( DieInt dieFromDraft, String inDeCrement){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, PlayerZone pl){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone pl){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList,PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}
