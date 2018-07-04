package it.polimi.ingsw.LM26.model.Cards.ToolCardsDecorator;

import it.polimi.ingsw.LM26.controller.PlaceDie;
import it.polimi.ingsw.LM26.model.Cards.ToolCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.util.ArrayList;

import static it.polimi.ingsw.LM26.model.SingletonModel.singletonModel;

public class MoveTwoDice4 extends ToolCardDecorator {

    private ToolCard toolcard = null;

    public MoveTwoDice4() {
    }

    public MoveTwoDice4(ToolCard toolcard) {

        this.toolcard = toolcard;

        this.type="MoveTwoDice4";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play ( ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList, PlayerZone player ) {

        ArrayList <DieInt> dieList=new ArrayList<DieInt>();

        for(int i=0; i<fromBoxList.size(); i++)

            if( !fromBoxList.get(i).isIsPresent() || toBoxList.get(i).isIsPresent() )

                return false;

            else { dieList.add(fromBoxList.get(i).getDie());

                fromBoxList.get(i).free();
            }

        for(int j=0; j<fromBoxList.size(); j++){

            PlaceDie placement = new PlaceDie(dieList.get(j), toBoxList.get(j) , player);

            if (!placement.placeDie() ) {

                System.out.println("error " + j +" placement");

                for(int k=0; k<fromBoxList.size(); k++) {

                    fromBoxList.get(k).setDie(dieList.get(k));

                    toBoxList.get(k).free();
                }

                return false;
            }
        }

        return true;
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

    @Override
    public void rewrite() {

        this.type="MoveTwoDice4";

        this.typeToolCard = "ToolCard";
    }

    @Override
    public boolean play(Box fromBox, Box toBox,  PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, Box toBox,PlayerZone player){

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, DieInt dieFromRoundTrack) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, String inDeCrement) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt dieFromDraft, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(int number, Box toBox, PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }

    @Override
    public boolean play(DieInt fromRoundTrack, ArrayList<Box> fromBoxList, ArrayList<Box> toBoxList,PlayerZone player) {

        throw new UnsupportedOperationException("Not supported here");
    }
}



