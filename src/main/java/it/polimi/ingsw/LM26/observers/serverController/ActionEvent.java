package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.model.Cards.ToolCardInt;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ClassMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class ActionEvent extends ClassMessage implements Serializable {

    private int ID;
    private int player=-1;
    private int number;
    private Box fromBox1=null;
    private Box toBox1=null;
    private int CardID=-1;

    private ArrayList<Box> fromBoxList=null;
    private ArrayList<Box> toBoxList=null;
    private DieInt dieFromDraft=null;
    private DieInt dieFromRoundTrack=null;
    private String inDeCrement=null;
    private Boolean noAction=false;
    private Boolean menu=false;
    private int[] rTrackCoordinates=null;


        /*
        CardID is from 1 not 0!

        event 1: place die. to set: DieFromDraf, ToBox1, Player.
        event 2: use card 2, 3. to set: CardInt, FromBox1, ToBox1, Player.
        event 3: use card 4. to set: CardInt,  ArrayList FromBox, ArrayList ToBox Player.
        event 4: use card 9. to set: CardInt, DieFromDraft, ToBox1, Player.
        event 5: use card 5. to set: CardInt, DieFromDraft, DieFromRoundTrack, Player.
        event 6: use card 1. to set: CardInt, DieFromDraft, InDeCrement, Player.
        event 7: use card 6, 10, 11. to set: CardInt, DieFromDraft, Player.
        event 8: use card 7,8. to set: CardInt, Player.
        event 9: use card 11. to set: CardInt, number, toBox1; (second action pf card 11)
        event 10: use card 12. to set: CardInt, DieFromRoundTrack, ArrayList FromBox, ArrayList ToBox
        event 11: no action: set boolean.
        event 12: ask menu: player, set boolean.
        */


    public int getCardID() { return CardID; }

    public void setCardID(int cardID) { CardID = cardID; }

    public int getId() { return ID; }

    public void setId(int id) {
        this.ID = id;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Box getFromBox1() {
        return fromBox1;
    }

    public void setFromBox1(Box fromBox1) {
        this.fromBox1 = fromBox1;
    }

    public Box getToBox1() {
        return toBox1;
    }

    public void setToBox1(Box toBox1) {
        this.toBox1 = toBox1;
    }

    public DieInt getDieFromDraft() {
        return dieFromDraft;
    }

    public void setDieFromDraft(DieInt dieFromDraft) {
        this.dieFromDraft =dieFromDraft;
    }

    public DieInt getDieFromRoundTrack() {
        return dieFromRoundTrack;
    }

    public void setDieFromRoundTrack(DieInt dieFromRoundTrack) { this.dieFromRoundTrack = dieFromRoundTrack; }

    public String getInDeCrement() {
        return inDeCrement;
    }

    public void setInDeCrement(String inDeCrement) {
        this.inDeCrement = inDeCrement;
    }

    public Boolean getMenu() { return menu; }

    public void setMenu(Boolean menu) { this.menu = menu; }

    public Boolean getNoAction() { return noAction; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public ArrayList<Box> getFromBoxList() {
        return fromBoxList;
    }

    public void setFromBoxList(ArrayList<Box> fromBoxList) {
        this.fromBoxList = fromBoxList;
    }

    public ArrayList<Box> getToBoxList() {
        return toBoxList;
    }

    public void setToBoxList(ArrayList<Box> toBoxList) {
        this.toBoxList = toBoxList;
    }

    public void setNoAction(Boolean noAction) { this.noAction = noAction; }

    public int[] getrTrackCoordinates() { return rTrackCoordinates; }

    public void setrTrackCoordinates(int[] rTrackCoordinates) { this.rTrackCoordinates = rTrackCoordinates; }


    @Override
    public void accept(VisitorInt visitorInt) {
        visitorInt.visitActionEvent(this);
    }

    public void rewrite(){

        //card.rewrite();
        if(fromBox1!=null)
            fromBox1.rewrite();
        if(toBox1!= null)
            toBox1.rewrite();
        if(toBoxList!=null){
            for (int i=0; i< toBoxList.size(); i++){
                toBoxList.get(i).rewrite();
            }
        }
        if(fromBoxList!=null){
            for(int i=0; i< fromBoxList.size(); i++ ){
                fromBoxList.get(i).rewrite();
            }
        }
        if(dieFromDraft!= null)
            dieFromDraft.rewrite();
        if(dieFromRoundTrack!= null)
            dieFromRoundTrack.rewrite();

    }
}
